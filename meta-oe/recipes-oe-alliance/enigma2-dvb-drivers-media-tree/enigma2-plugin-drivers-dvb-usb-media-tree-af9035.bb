SUMMARY = "USB DVB driver for Afatech 9035 chipset"
inherit allarch

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = " \
    enigma2-plugin-drivers-dvb-usb-af9035 \
    kernel-module-dvb-usb-af9035-${KV} \
    kernel-module-af9033-${KV} \
    kernel-module-tua9001-${KV} \
    kernel-module-mxl5007t-${KV} \
    kernel-module-tda18218-${KV} \
    kernel-module-it913x-${KV} \
    kernel-module-fc0012-${KV} \
    kernel-module-fc2580-${KV} \
    kernel-module-fc0011-${KV} \
    kernel-module-dvb-usb-v2-${KV} \
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
PR = "r1"

ALLOW_EMPTY:${PN} = "1"
