SUMMARY = "USB DVB driver for AS102 chipset"
inherit allarch

require conf/license/license-gplv2.inc

RRECOMMENDS:${PN} = " \
    media-tree-module-mediatree-dvb-core \
    media-tree--module-dvb-as102 \
    media-tree--module-as102-fe \
    firmware-as102-data1-st \
    firmware-as102-data2-st \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"
