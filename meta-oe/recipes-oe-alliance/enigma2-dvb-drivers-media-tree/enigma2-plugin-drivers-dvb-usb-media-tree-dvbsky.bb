SUMMARY = "USB DVB driver for DVB-SKY Family"
inherit allarch

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = " \
    enigma2-plugin-drivers-dvb-usb-dvbsky-classic \
    ${DVBSKYPROVIDER}-module-dvb-usb-dvbsky \
    ${DVBSKYPROVIDER}-module-m88ds3103 \
    ${DVBSKYPROVIDER}-module-dvbsky-m88ds3103 \
    ${DVBSKYPROVIDER}-module-dvbsky-m88rs6000 \
    ${DVBSKYPROVIDER}-module-sit2fe \
    ${DVBSKYPROVIDER}-module-si2168 \
    ${DVBSKYPROVIDER}-module-si2157 \
    ${DVBSKYPROVIDER}-module-ts2020 \
    "

RRECOMMENDS:${PN} = " \
    media-tree-module-mediatree-dvb-core \
    media-tree-module-dvb-usb-dvbsky \
    media-tree-module-m88ds3103 \
    media-tree-module-dvbsky-m88ds3103 \
    media-tree-module-dvbsky-m88rs6000 \
    media-tree-module-si2168 \
    media-tree-module-si2157 \
    media-tree-module-ts2020 \
    firmware-dvb-usb-s660 \
    firmware-dvb-fe-ds3000 \
    firmware-dvb-fe-ds3xxx \
    firmware-dvb-fe-si2168 \
    "

PV = "1.0"
PR = "r1"

ALLOW_EMPTY:${PN} = "1"
