SUMMARY = "USB DVB driver for Afatech 9015 chipset"
inherit allarch

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = " \
    enigma2-plugin-drivers-dvb-usb-af9015 \
    kernel-module-dvb-usb-af9015 \
    kernel-module-af9013 \
    kernel-module-dvb-pll \
    kernel-module-dvb-usb \
    kernel-module-mc44s803 \
    kernel-module-mt2060 \
    kernel-module-mxl5005s \
    kernel-module-mxl5007t \
    kernel-module-qt1010 \
    kernel-module-tda18218 \
    kernel-module-tda18271 \
    "

RRECOMMENDS:${PN} = " \
    media-tree-module-mediatree-dvb-core \
    media-tree-module-dvb-usb-af9015 \
    media-tree-module-af9013 \
    media-tree-module-dvb-pll \
    media-tree-module-dvb-usb \
    media-tree-module-mc44s803 \
    media-tree-module-mt2060 \
    media-tree-module-mxl5005s \
    media-tree-module-mxl5007t \
    media-tree-module-qt1010 \
    media-tree-module-tda18218 \
    media-tree-module-tda18271 \
    firmware-dvb-usb-af9015 \
    firmware-dvb-fe-af9013 \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"
