
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://NetworkManager.conf"

PACKAGECONFIG:append = " modemmanager "

do_install:append() {
    install -d ${D}${sysconfdir}/NetworkManager
    install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
}