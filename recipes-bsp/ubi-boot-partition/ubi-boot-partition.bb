SUMMARY = "This package creates the ubifs boot partition for the AutomationOne Gateways"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://boot.cmd"

DEPENDS = "u-boot-tools-native mtd-utils-native"

inherit deploy

do_compile() {
    mkdir -p ${WORKDIR}/boot

    # Populate boot Partition

    mkimage -A arm -O linux -T script -C none -a 0 -e 0 -d ${WORKDIR}/boot.cmd ${WORKDIR}/boot/boot.scr

    # Create boot partition
    mkfs.ubifs -o "${WORKDIR}/boot.ubifs" -r "${WORKDIR}/boot" ${MKUBIFS_ARGS}
}

do_deploy(){
    cp ${WORKDIR}/boot.ubifs ${DEPLOYDIR}/${PN}.ubifs
    cp ${WORKDIR}/boot/boot.scr ${DEPLOYDIR}/ubi-boot.scr
}

addtask do_deploy after do_compile
