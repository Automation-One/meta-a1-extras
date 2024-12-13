
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://NetworkManager.conf \
    file://end0.nmconnection \
    file://end0-dhcp.nmconnection \
    file://end1.nmconnection \
    file://end1-dhcp.nmconnection \
    "

PACKAGECONFIG:append = " modemmanager "

do_install:append() {
    install -d ${D}${sysconfdir}/NetworkManager
    install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf

    install -d ${D}${sysconfdir}/NetworkManager/system-connections
    install -m 0600 ${WORKDIR}/end0.nmconnection ${D}${sysconfdir}/NetworkManager/system-connections/end0.nmconnection
    install -m 0600 ${WORKDIR}/end0-dhcp.nmconnection ${D}${sysconfdir}/NetworkManager/system-connections/end0-dhcp.nmconnection
    install -m 0600 ${WORKDIR}/end1.nmconnection ${D}${sysconfdir}/NetworkManager/system-connections/end1.nmconnection
    install -m 0600 ${WORKDIR}/end1-dhcp.nmconnection ${D}${sysconfdir}/NetworkManager/system-connections/end1-dhcp.nmconnection

    if [ "${END0_DHCP}" = "1" ] || [ "${END0_DHCP}" = "true" ]; then
        sed -i 's/autoconnect=false/autoconnect=true/' ${D}${sysconfdir}/NetworkManager/system-connections/end0-dhcp.nmconnection
    fi
    if [ "${END1_DHCP}" = "1" ] || [ "${END1_DHCP}" = "true" ]; then
        sed -i 's/autoconnect=false/autoconnect=true/' ${D}${sysconfdir}/NetworkManager/system-connections/end1-dhcp.nmconnection
    fi
}