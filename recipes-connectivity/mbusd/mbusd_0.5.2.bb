SUMMARY = "mbusd - Open-source Modbus TCP to Modbus RTU gateway"
DESCRIPTION = "mbusd is an open-source Modbus TCP to Modbus RTU gateway."
HOMEPAGE = "https://github.com/3cky/mbusd"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=57f1ea8a45aa7ea1e5cc0687486c7815"

SRC_URI = "git://github.com/3cky/mbusd.git;protocol=https;tag=v${PV};branch=master"

S = "${WORKDIR}/git"

inherit cmake

DEPENDS += "pkgconfig-native"

EXTRA_OECMAKE += "-DSYSTEMD_SERVICES_INSTALL_DIR=/etc/systemd/system"

# Conditionally add systemd support if enabled in the distro
python __anonymous() {
    if 'systemd' in d.getVar('DISTRO_FEATURES').split():
        d.appendVar('DEPENDS', ' systemd')
}
