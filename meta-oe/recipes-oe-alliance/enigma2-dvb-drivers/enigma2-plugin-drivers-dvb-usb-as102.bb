SUMMARY = "USB DVB driver for AS102 chipset"
inherit allarch

require conf/license/license-gplv2.inc

DVBPROVIDER ?= "kernel"

RRECOMMENDS:${PN} = " \
    ${@bb.utils.contains("DVBPROVIDER", "media-tree", "media-tree-module-mediatree-dvb-core", "", d)} \
    ${@bb.utils.contains("DVBPROVIDER", "media-tree", "media-tree-module-mc", "", d)} \
    ${DVBPROVIDER}-module-dvb-as102 \
    ${DVBPROVIDER}-module-as102-fe \
    firmware-as102-data1-st \
    firmware-as102-data2-st \
    "

PV = "1.0"
PR = "r1"

ALLOW_EMPTY:${PN} = "1"
