SUMMARY = "Set the authrorized_keys file for root user"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


INHIBIT_DEFAULT_DEPS = "1"
do_compile[noexec] = "1"



python () {
    authorized_keys = d.getVar('ROOT_AUTHORIZED_KEYS', True)
    if not authorized_keys:
        authorized_keys = 'authorized_keys'
        d.setVar('ROOT_AUTHORIZED_KEYS', authorized_keys)
}

SRC_URI = "file://${ROOT_AUTHORIZED_KEYS}"


do_install() {
    install -d ${D}/home/root/.ssh
    install -m 0600 ${WORKDIR}/${ROOT_AUTHORIZED_KEYS} ${D}/home/root/.ssh/authorized_keys
}

FILES:${PN} += "/home/root/.ssh/authorized_keys"