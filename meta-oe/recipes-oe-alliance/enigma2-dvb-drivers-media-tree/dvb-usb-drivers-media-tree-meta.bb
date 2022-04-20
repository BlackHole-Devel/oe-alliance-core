SUMMARY = "meta file for USB DVB drivers"
inherit packagegroup

require conf/license/license-gplv2.inc

DEPENDS = "\
    enigma2-plugin-drivers-dvb-usb-media-tree-af9015 \
    enigma2-plugin-drivers-dvb-usb-media-tree-af9035 \
    enigma2-plugin-drivers-dvb-usb-media-tree-as102 \
    enigma2-plugin-drivers-dvb-usb-media-tree-dib0700 \
    enigma2-plugin-drivers-dvb-usb-media-tree-dvbsky \
    enigma2-plugin-drivers-dvb-usb-media-tree-rtl2832 \
    enigma2-plugin-drivers-dvb-usb-media-tree-siano \
    "

PR = "r1"
