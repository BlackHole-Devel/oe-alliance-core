SUMMARY = "udpxy"
MAINTAINER = "Pavel V. Cherenkov"
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://README;md5=f210c6f38d8c7db12fdfd009dcd9438c"
LIC_FILES_CHKSUM:sh4 = "file://README;md5=b0c7b851d6d40e5194e73ca66db0e257"
inherit gitpkgv

SRCREV = "${AUTOREV}"
SRCREV:sh4 = "ab25cc685cc31b78a1a842c012bf7e30331bab2b"
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit autotools-brokensep pkgconfig

SRC_URI = "git://github.com/pcherenkov/udpxy.git;protocol=https file://udpxy.sh"
CFLAGS:append = " -Wno-format-truncation "

S = "${WORKDIR}/git/chipmunk"

FILES:${PN} = "${bindir}/* /etc/init.d/udpxy.sh"

do_compile() {
    make -f Makefile udpxy
}

do_install() {
    install -d ${D}/etc/init.d
    install -m 755 ${WORKDIR}/udpxy.sh ${D}/etc/init.d/
    install -d ${D}/${bindir}
    install -m 755 ${S}/udpxy ${D}/${bindir}
}

INITSCRIPT_NAME = "udpxy.sh"
INITSCRIPT_PARAMS = "defaults"

inherit update-rc.d
