SUMMARY = "USB DVB driver for Realtek RTL2832 chipset"
inherit allarch

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = " \
    enigma2-plugin-drivers-dvb-usb-rtl2832 \
    kernel-module-dvb-usb-rtl2832-${KV} \
    kernel-module-dvb-usb-rtl28xxu-${KV} \
    kernel-module-rtl2832-${KV} \
    kernel-module-e4000-${KV} \
    kernel-module-r820t-${KV} \
    kernel-module-mt2266-${KV} \
    kernel-module-fc0012-${KV} \
    kernel-module-fc0013-${KV} \
    "

RRECOMMENDS:${PN} = " \
    media-tree-module-mediatree-dvb-core \
    media-tree-module-dvb-usb-rtl2832 \
    media-tree-module-dvb-usb-rtl28xxu \
    media-tree-module-rtl2832 \
    media-tree-module-e4000 \
    media-tree-module-r820t \
    media-tree-module-mt2266 \
    media-tree-module-fc0012 \
    media-tree-module-fc0013 \
    firmware-dvb-usb-af9035-01 \
    firmware-dvb-usb-af9035-02 \
    firmware-dvb-usb-af9015 \
    "

PV = "1.0"
PR = "r1"

ALLOW_EMPTY:${PN} = "1"
