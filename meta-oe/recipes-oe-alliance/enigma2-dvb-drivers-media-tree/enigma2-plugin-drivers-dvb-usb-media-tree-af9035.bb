SUMMARY = "USB DVB driver for Afatech 9035 chipset"
inherit allarch

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = " \
    enigma2-plugin-drivers-dvb-usb-af9035 \
    kernel-module-dvb-usb-af9035 \
    kernel-module-af9033 \
    kernel-module-tua9001 \
    kernel-module-mxl5007t \
    kernel-module-tda18218 \
    kernel-module-it913x \
    kernel-module-fc0012 \
    kernel-module-fc2580 \
    kernel-module-fc0011 \
    kernel-module-dvb-usb-v2 \
    "

RRECOMMENDS:${PN} = " \
    media-tree-module-mediatree-dvb-core \
    media-tree-module-mediatree-dvb-usb-v2 \
    media-tree-module-dvb-usb-af9035 \
    media-tree-module-af9033 \
    media-tree-module-tua9001 \
    media-tree-module-mxl5007t \
    media-tree-module-tda18218 \
    media-tree-module-it913x \
    media-tree-module-fc0012 \
    media-tree-module-fc2580 \
    media-tree-module-fc0011 \
    firmware-dvb-usb-af9035-01 \
    firmware-dvb-usb-af9035-02 \
    firmware-dvb-usb-it913x \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"
