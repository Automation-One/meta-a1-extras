SUMMARY = "Creates a UBI image for a AutomationOne Gateway"
LICENSE = "MIT"

require a1-image.bb
inherit image_types_a1_ubi

DEPENDS += " ubi-boot-partition"

IMAGE_FSTYPES:append = " ubifs a1_ubi"
IMAGE_FEATURES += " overlayfs-etc read-only-rootfs"

create_data_mountpoint() {
    mkdir -p ${IMAGE_ROOTFS}/data
}
ROOTFS_POSTPROCESS_COMMAND += "create_data_mountpoint; "


do_create_data_ubifs() {
    mkdir -p ${WORKDIR}/ubi-staging

    rm -rf ${WORKDIR}/data
    mkdir -p ${WORKDIR}/data

    mkfs.ubifs -o "${WORKDIR}/ubi-staging/data.ubifs" -r "${WORKDIR}/data" ${MKUBIFS_ARGS}
}
addtask do_create_data_ubifs after do_rootfs do_populate_sysroot before do_image_ubi_a1

do_image_ubi_a1[depends] += "ubi-boot-partition:do_deploy"
