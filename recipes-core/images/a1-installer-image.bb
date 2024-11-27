SUMMARY = "Automation One Installer Image to NAND device"
LICENSE = "MIT"

require a1-image.bb

A1_INSTALLER_IMAGE_ROOTFS = "a1-ubi-image-${MACHINE}"
A1_INSTALLER_U_BOOT = "flash.bin"
A1_INSTALLER_DIR = "/installer-data"

IMAGE_INSTALL += " a1-installer "

do_rootfs[depends] += "a1-ubi-image:do_image_complete"


copy_a1_image_files() {
    mkdir -p ${IMAGE_ROOTFS}${A1_INSTALLER_DIR}

    cp ${DEPLOY_DIR_IMAGE}/${A1_INSTALLER_IMAGE_ROOTFS}.ubi ${IMAGE_ROOTFS}${A1_INSTALLER_DIR}/rootfs.ubi
    cp ${DEPLOY_DIR_IMAGE}/${A1_INSTALLER_U_BOOT} ${IMAGE_ROOTFS}${A1_INSTALLER_DIR}/flash.bin
}

ROOTFS_POSTPROCESS_COMMAND += "copy_a1_image_files;"
