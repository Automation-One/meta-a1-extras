inherit image_types

do_image_a1_ubi[depends] += "mtd-utils-native:do_populate_sysroot"
IMAGE_TYPEDEP:a1_ubi:append = " ubifs "



create_ubi_config() {
    cat > ${WORKDIR}/a1-ubi-${IMAGE_NAME}.cfg <<EOF
[boot]
mode=ubi
image=${WORKDIR}/ubi-staging/boot.ubifs
vol_id=0
vol_size=16MiB
vol_type=static
vol_name=boot

[rootfsA]
mode=ubi
image=${WORKDIR}/ubi-staging/rootfsA.ubifs
vol_id=1
vol_size=120MiB
vol_type=static
vol_name=rootfsA

[rootfsB]
mode=ubi
image=${WORKDIR}/ubi-staging/rootfsB.ubifs
vol_id=2
vol_size=120MiB
vol_type=static
vol_name=rootfsB

[data]
mode=ubi
image=${WORKDIR}/ubi-staging/data.ubifs
vol_id=3
vol_size=16MiB
vol_type=dynamic
vol_name=data
vol_flags=autoresize
EOF
}


IMAGE_CMD:a1_ubi() {
    create_ubi_config

    mkdir -p ${WORKDIR}/ubi-staging

    # Create data UBIFS image
    mkdir -p ${WORKDIR}/data
    mkfs.ubifs -o "${WORKDIR}/ubi-staging/data.ubifs" -r "${WORKDIR}/data" ${MKUBIFS_ARGS}

    # Stage boot partition
    ln -sf ${DEPLOY_DIR_IMAGE}/ubi-boot-partition.ubifs ${WORKDIR}/ubi-staging/boot.ubifs

    # Stage rootfs image
    ln -sf ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.ubifs ${WORKDIR}/ubi-staging/rootfsA.ubifs
    ln -sf ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.ubifs ${WORKDIR}/ubi-staging/rootfsB.ubifs

    ubinize -o ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ubi ${UBINIZE_ARGS} ${WORKDIR}/a1-ubi-${IMAGE_NAME}.cfg
    ln -sf ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.ubi ${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.ubi
}