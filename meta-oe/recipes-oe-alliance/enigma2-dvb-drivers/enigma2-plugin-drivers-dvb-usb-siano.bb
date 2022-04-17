SUMMARY = "USB DVB driver for Siano chipset"
inherit allarch

require conf/license/license-gplv2.inc

DVBPROVIDER ?= "kernel"

RRECOMMENDS:${PN} = " \
    ${@bb.utils.contains("DVBPROVIDER", "media-tree", "media-tree-module-mediatree-dvb-core", "", d)} \
    ${@bb.utils.contains("DVBPROVIDER", "media-tree", "media-tree-module-mc", "", d)} \
    ${@bb.utils.contains("DVBPROVIDER", "media-tree", "media-tree-module-smsmdtv", "", d)} \
    ${DVBPROVIDER}-module-smsusb \
    ${DVBPROVIDER}-module-smsdvb \
    ${@bb.utils.contains("MACHINE_FEATURES", "legacykernel", \
    " \
    ${DVBPROVIDER}-module-smsmdtv \
    " , "", d)} \
    firmware-dvb-siano \
    firmware-dvb-nova-12mhz-b0 \
    firmware-isdbt-nova-12mhz-b0 \
    firmware-dvb-usb-siano-sms2200 \
    "

PV = "1.0"
PR = "r1"

ALLOW_EMPTY:${PN} = "1"
