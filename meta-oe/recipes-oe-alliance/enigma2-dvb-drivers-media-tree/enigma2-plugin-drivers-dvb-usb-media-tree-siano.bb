SUMMARY = "USB DVB driver for Siano chipset"
inherit allarch

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = " \
    enigma2-plugin-drivers-dvb-usb-kernelsiano \
    kernel-module-smsmdtv \
    kernel-module-smsusb \
    kernel-module-smsdvb \
    "

RRECOMMENDS:${PN} = " \
    media-tree-module-mediatree-dvb-core \
    media-tree-module-smsmdtv \
    media-tree-module-smsusb \
    media-tree-module-smsdvb \
    firmware-dvb-siano \
    firmware-dvb-nova-12mhz-b0 \
    firmware-isdbt-nova-12mhz-b0 \
    firmware-dvb-usb-siano-sms2200 \
    "

PV = "1.0"
PR = "r1"

ALLOW_EMPTY:${PN} = "1"
