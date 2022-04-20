SUMMARY = "USB DVB driver for Afatech 9015 chipset"
inherit allarch

require conf/license/license-gplv2.inc

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
