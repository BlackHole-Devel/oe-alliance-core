SUMMARY = "USB DVB driver for dib0700 chipset"
inherit allarch

require conf/license/license-gplv2.inc

RCONFLICTS:${PN} = " \
    kernel-module-dvb-usb-dib0700 \
    kernel-module-dvb-usb-dibusb-common \
    kernel-module-dvb-usb-dibusb-mc \
    kernel-module-dib0090 \
    kernel-module-dib3000mb \
    kernel-module-dib3000mc \
    kernel-module-dibx000-common \
    kernel-module-mt2060 \
    kernel-module-dib7000m \
    kernel-module-dib7000p \
    kernel-module-dib0070 \
    kernel-module-dib8000 \
    kernel-module-dvb-usb \
    kernel-module-tuner-xc2028 \
    kernel-module-fc0013 \
    kernel-module-mt2266 \
    kernel-module-tda18250 \
    kernel-module-mn88472 \
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
PR = "r0"

ALLOW_EMPTY:${PN} = "1"
