SUMMARY = "media tree drivers"
HOMEPAGE = "http://linuxtv.org/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"

inherit module

PACKAGES_DYNAMIC += "media-tree-module-*"

do_configure() {
    install -m 0644 ${WORKDIR}/defconfig ${S}/v4l/.config
}

do_compile() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake KERNEL_PATH=${STAGING_KERNEL_BUILDDIR}   \
        KDIR="${STAGING_KERNEL_DIR}" \
        DIR="${STAGING_KERNEL_DIR}" \
        KERNEL_VERSION=${KERNEL_VERSION} \
        CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
        AR="${KERNEL_AR}" \
        O=${STAGING_KERNEL_BUILDDIR} \
       ${MAKE_TARGETS}
}

do_install() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake DEPMOD=echo DESTDIR="${D}" \
        KDIR="${STAGING_KERNEL_DIR}" \
        DIR="${STAGING_KERNEL_DIR}" \
        KERNEL_VERSION=${KERNEL_VERSION} \
        KERNELRELEASE=${KERNEL_VERSION} \
        CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
        O=${STAGING_KERNEL_BUILDDIR} \
        install
}

PACKAGES = "media-tree-modules-dvbs ${PN}-tests"
FILES:${PN} = ""
ALLOW_EMPTY:${PN} = "1"

FILES:${PN}-tests += " \
    ${nonarch_base_libdir}/firmware/* \
    ${nonarch_base_libdir}/modules/*/modules.* \
    "

pkg_postinst:modules() {
#!/bin/sh
if [ -z "$D" ]; then
	depmod -a ${KERNEL_VERSION}
else
	# image.bbclass will call depmodwrapper after everything is installed,
	# no need to do it here as well
	:
fi
}

pkg_postrm:modules () {
#!/bin/sh
if [ -z "$D" ]; then
	depmod -a ${KERNEL_VERSION}
else
	depmodwrapper -a -b $D ${KERNEL_VERSION}
fi
}

autoload_postinst_fragment() {
if [ x"$D" = "x" ]; then
    modprobe %s || true
fi
}

python split_kernel_module_packages () {
    import re

    modinfoexp = re.compile("([^=]+)=(.*)")
    kerverrexp = re.compile('^(.*-hh.*)[\.\+].*$')
    depmodpat0 = re.compile("^(.*\.k?o):..*$")
    depmodpat1 = re.compile("^(.*\.k?o):\s*(.*\.k?o)\s*$")
    depmodpat2 = re.compile("^(.*\.k?o):\s*(.*\.k?o)\s*\\\$")
    depmodpat3 = re.compile("^\t(.*\.k?o)\s*\\\$")
    depmodpat4 = re.compile("^\t(.*\.k?o)\s*$")

    def extract_modinfo(file):
        import tempfile, subprocess
        tempfile.tempdir = d.getVar("WORKDIR", True)
        tf = tempfile.mkstemp()
        tmpfile = tf[1]
        cmd = "%sobjcopy -j .modinfo -O binary %s %s" % (d.getVar("HOST_PREFIX", True) or "", file, tmpfile)
        subprocess.call(cmd, shell=True)
        f = open(tmpfile)
        l = f.read().split("\000")
        f.close()
        os.close(tf[0])
        os.unlink(tmpfile)
        vals = {}
        for i in l:
            m = modinfoexp.match(i)
            if not m:
                continue
            vals[m.group(1)] = m.group(2)
        return vals

    def parse_depmod():

        dvar = d.getVar('PKGD', True)

        kernelver = d.getVar('KERNEL_VERSION', True)
        kernelver_stripped = kernelver
        m = kerverrexp.match(kernelver)
        if m:
            kernelver_stripped = m.group(1)
        staging_kernel_dir = d.getVar("STAGING_KERNEL_BUILDDIR", True)
        system_map_file = "%s/boot/System.map-%s" % (dvar, kernelver)
        if not os.path.exists(system_map_file):
            system_map_file = "%s/System.map-%s" % (staging_kernel_dir, kernelver)
            if not os.path.exists(system_map_file):
                bb.fatal("System.map-%s does not exist in '%s/boot' nor STAGING_KERNEL_BUILDDIR '%s'" % (kernelver, dvar, staging_kernel_dir))

        cmd = "depmod -n -a -b %s -F %s %s" % (dvar, system_map_file, kernelver_stripped)
        f = os.popen(cmd, 'r')

        deps = {}
        line = f.readline()
        while line:
            if not depmodpat0.match(line):
                line = f.readline()
                continue
            m1 = depmodpat1.match(line)
            if m1:
                deps[m1.group(1)] = m1.group(2).split()
            else:
                m2 = depmodpat2.match(line)
                if m2:
                    deps[m2.group(1)] = m2.group(2).split()
                    line = f.readline()
                    m3 = depmodpat3.match(line)
                    while m3:
                        deps[m2.group(1)].extend(m3.group(1).split())
                        line = f.readline()
                        m3 = depmodpat3.match(line)
                    m4 = depmodpat4.match(line)
                    deps[m2.group(1)].extend(m4.group(1).split())
            line = f.readline()
        f.close()
        return deps

    def get_dependencies(file, pattern, format):
        # file no longer includes PKGD
        file = file.replace(d.getVar('PKGD', True) or '', '', 1)
        # instead is prefixed with ${nonarch_base_libdir}/modules/${KERNEL_VERSION}
        file = file.replace("${nonarch_base_libdir}/modules/%s/" % d.getVar('KERNEL_VERSION', True) or '', '', 1)

        if file in module_deps:
            dependencies = []
            for i in module_deps[file]:
                m = re.match(pattern, os.path.basename(i))
                if not m:
                    continue
                on = legitimize_package_name(m.group(1))
                dependency_pkg = format % on
                dependencies.append(dependency_pkg)
            return dependencies
        return []

    def frob_metadata(file, pkg, pattern, format, basename):
        vals = extract_modinfo(file)

        dvar = d.getVar('PKGD', True)

        # If autoloading is requested, output /etc/modules-load.d/<name>.conf and append
        # appropriate modprobe commands to the postinst
        autoload = d.getVar('module_autoload_%s' % basename, True)
        if autoload:
            name = '%s/etc/modules-load.d/%s.conf' % (dvar, basename)
            f = open(name, 'w')
            for m in autoload.split():
                f.write('%s\n' % m)
            f.close()
            postinst = d.getVar('pkg_postinst:%s' % pkg, True)
            if not postinst:
                bb.fatal("pkg_postinst:%s not defined" % pkg)
            postinst += d.getVar('autoload_postinst_fragment', True) % autoload
            d.setVar('pkg_postinst:%s' % pkg, postinst)

        # Write out any modconf fragment
        modconf = d.getVar('module_conf_%s' % basename, True)
        if modconf:
            name = '%s/etc/modprobe.d/%s.conf' % (dvar, basename)
            f = open(name, 'w')
            f.write("%s\n" % modconf)
            f.close()

        files = d.getVar('FILES:%s' % pkg, True)
        files = "%s /etc/modules-load.d/%s.conf /etc/modprobe.d/%s.conf" % (files, basename, basename)
        d.setVar('FILES:%s' % pkg, files)

        if "description" in vals:
            old_desc = d.getVar('DESCRIPTION:' + pkg, True) or ""
            d.setVar('DESCRIPTION:' + pkg, old_desc + "; " + vals["description"])

        rdepends = bb.utils.explode_dep_versions2(d.getVar('RDEPENDS:' + pkg, True) or "")
        for dep in get_dependencies(file, pattern, format):
            if not dep in rdepends:
                rdepends[dep] = []
        d.setVar('RDEPENDS:' + pkg, bb.utils.join_deps(rdepends, commasep=False))

    module_deps = parse_depmod()
    module_regex = '^(.*)\.k?o$'
    module_pattern = 'media-tree-module-%s'

    postinst = d.getVar('pkg_postinst:modules', True)
    postrm = d.getVar('pkg_postrm:modules', True)

    modules = do_split_packages(d, root='/lib/modules', file_regex=module_regex, output_pattern=module_pattern, description='%s kernel module', postinst=postinst, postrm=postrm, recursive=True, hook=frob_metadata, extra_depends='kernel-%s' % (d.getVar("KERNEL_VERSION", True)))
    if modules:
        metapkg = d.getVar('KERNEL_MODULES_META_PACKAGE', True)
        d.appendVar('RDEPENDS:' + metapkg, ' '+' '.join(modules))

    # If modules-load.d and modprobe.d are empty at this point, remove them to
    # avoid warnings. removedirs only raises an OSError if an empty
    # directory cannot be removed.
    dvar = d.getVar('PKGD', True)
    for dir in ["%s/etc/modprobe.d" % (dvar), "%s/etc/modules-load.d" % (dvar), "%s/etc" % (dvar)]:
        if len(os.listdir(dir)) == 0:
            os.rmdir(dir)
}
