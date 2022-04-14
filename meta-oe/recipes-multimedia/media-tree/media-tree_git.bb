DEPENDS = "virtual/kernel module-init-tools"
RDEPENDS:${PN} += "kmod"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

SRC_DATE = "2022-03-16"
SRC_SHA = "71e6d0608e4d"
SRC_URI[sha256sum] = "d1ea0113abcaa711d635727d32a2ca4b9dc5980313180528fd22ec8eda615cb8"

SRCREV_mediabuild = "${AUTOREV}"
SRCREV_linux = "71e6d0608e4d1b79069990c7dacb3600ced28a3b"
SRCREV_FORMAT = "linux"
SRC_URI = "git://github.com/BlackHole-Devel/media_build.git;protocol=https;branch=oea;name=mediabuild \
           http://linuxtv.org/downloads/drivers/linux-media-${SRC_DATE}-${SRC_SHA}.tar.bz2;unpack=0 \
           file://defconfig \
"

PR = "r0"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "KDIR=${STAGING_KERNEL_DIR} SRCDIR=${STAGING_KERNEL_DIR} OUTDIR=${STAGING_KERNEL_BUILDDIR}"

do_populate_sysroot[noexec] = "1"

do_configure:prepend() {
    cp ${WORKDIR}/linux-media-${SRC_DATE}-${SRC_SHA}.tar.bz2 ${S}/linux/linux-media.tar.bz2
    md5sum ${S}/linux/linux-media.tar.bz2 > ${S}/linux/linux-media.tar.bz2.md5
    CUR=`pwd`
    rm -f v4l/.version

    #we use fixed version of media-tree (SRC_DATE/SRC_SHA) so we don't use automatically build config file
    #./build
    #make DIR=${STAGING_KERNEL_BUILDDIR} allyesconfig
    make -C linux untar
    cd $CUR
}

require media-tree-modules.inc
