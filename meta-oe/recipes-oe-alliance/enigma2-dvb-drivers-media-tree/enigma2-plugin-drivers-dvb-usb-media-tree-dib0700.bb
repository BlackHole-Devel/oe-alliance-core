SUMMARY = "USB DVB driver for dib0700 chipset"
inherit allarch

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = " \
    enigma2-plugin-drivers-dvb-usb-dib0700 \
    kernel-module-dvb-usb-dib0700-${KV} \
    kernel-module-dvb-usb-dibusb-common-${KV} \
    kernel-module-dvb-usb-dibusb-mc-${KV} \
    kernel-module-dib0090-${KV} \
    kernel-module-dib3000mb-${KV} \
    kernel-module-dib3000mc-${KV} \
    kernel-module-dibx000-common-${KV} \
    kernel-module-mt2060-${KV} \
    kernel-module-dib7000m-${KV} \
    kernel-module-dib7000p-${KV} \
    kernel-module-dib0070-${KV} \
    kernel-module-dib8000-${KV} \
    kernel-module-dvb-usb-${KV} \
    kernel-module-tuner-xc2028-${KV} \
    kernel-module-fc0013-${KV} \
    kernel-module-mt2266-${KV} \
    kernel-module-tda18250-${KV} \
    kernel-module-mn88472-${KV} \
    "

RRECOMMENDS:${PN} = " \
    media-tree-module-mediatree-dvb-core \
    media-tree-module-dvb-usb-dib0700 \
    media-tree-module-dvb-usb-dibusb-common \
    media-tree-module-dvb-usb-dibusb-mc \
    media-tree-module-dib0090 \
    media-tree-module-dib3000mb \
    media-tree-module-dib3000mc \
    media-tree-module-dibx000-common \
    media-tree-module-mt2060 \
    media-tree-module-dib7000m \
    media-tree-module-dib7000p \
    media-tree-module-dib0070 \
    media-tree-module-dib8000 \
    media-tree-module-dvb-usb \
    media-tree-module-tuner-xc2028 \
    media-tree-module-fc0013 \
    media-tree-module-mt2266 \
    media-tree-module-tda18250 \
    media-tree-module-mn88472 \
    media-tree-module-dib9000 \
    firmware-dvb-usb-dib0700-1.20 \
    firmware-dvb-usb-dibusb-5.0.0.11 \
    firmware-dvb-usb-dibusb-6.0.0.8 \
    firmware-dvb-usb-dibusb-an2235-01 \
    firmware-xc3028-v27 \
    firmware-xc3028l-v36 \
    firmware-mn8847x \
    "

PV = "1.0"
PR = "r1"

ALLOW_EMPTY:${PN} = "1"
