DESCRIPTION = "${HICHIPSET} libraries"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
DEPENDS = "zlib libpng freetype curl libxml2 libjpeg-turbo harfbuzz"
RDEPENDS:${PN} = "libbluray"
PR = "${SRCDATE}"

SRC_URI = "http://source.mynonpublic.com/abcom/${HICHIPSET}-libs-${SRCDATE}.zip"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

# Disable anything auto depending
EXCLUDE_FROM_SHLIBS = "1"

do_install() {
	install -d ${D}${libdir}/hisilicon
	install -m 0755 ${S}/hisilicon/* ${D}${libdir}/hisilicon
	install -m 0755 ${S}/ffmpeg/* ${D}${libdir}/hisilicon
	install -d ${D}/lib
	ln -sf /lib/ld-linux-armhf.so.3 ${D}${libdir}/hisilicon/ld-linux.so
}

do_package_qa() {
}

FILES:${PN} = "${libdir}/hisilicon/* /lib/*"

INSANE_SKIP:${PN} += "ldflags already-stripped dev-so"
