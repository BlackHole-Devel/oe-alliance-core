SUMMARY = "HD Skinpart for Mediaportal Metrix Style"
MAINTAINER = "nettraxx"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

require conf/license/license-gplv2.inc

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "1.0"
PKGV = "1.0"
VER ="1.0"
PR = "r1"

SRC_URI="git://github.com/pixbox-hdf/MediaPortalSkins.git;protocol=https"

S = "${WORKDIR}/git/MetrixHD"

FILES:${PN} = "${libdir}"

do_install() {
    install -d ${D}${libdir}
    cp -rp ${S}/usr/lib/* ${D}${libdir}/
}

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
