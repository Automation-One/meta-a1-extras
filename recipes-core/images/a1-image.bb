SUMMARY = "An image with various examples and demos tailored specifically for the AutomationOne Gateways"

LICENSE = "MIT"

inherit core-image
inherit extrausers

python () {
    if "debug-tweaks" in d.getVar('IMAGE_FEATURES'):
        return
    if d.getVar('ROOT_PASSWORD') and d.getVar('ROOT_PASSWORD_HASH'):
        bb.warnonce("ROOT_PASSWORD and ROOT_PASSWORD_HASH are mutually exclusive. Ignoring ROOT_PASSWORD.")
    if d.getVar('ROOT_PASSWORD_HASH'):
        d.setVar('EXTRA_USERS_PARAMS', r"usermod -p $(echo '${ROOT_PASSWORD_HASH}' | sed 's/\$/\\$/g') root;")
    elif d.getVar('ROOT_PASSWORD'):
        d.setVar('EXTRA_USERS_PARAMS', r"usermod -p $(openssl passwd ${ROOT_PASSWORD} | sed 's/\$/\\$/g') root;")
}

IMAGE_FEATURES += " allow-root-login "

IMAGE_INSTALL:append = " \
    modemmanager \
    networkmanager \
    networkmanager-nmcli \
    networkmanager-ppp \
    networkmanager-wifi \
    networkmanager-wwan \
    iptables \
    \
    curl \
    openssl \
    ca-certificates \
    openssh \
    \
    minicom \
    screen \
    \
    less \
    tree \
    \
    mtd-utils \
    mtd-utils-ubifs \
    \
    util-linux \
    parted \
    e2fsprogs \
    e2fsprogs-resize2fs \
    \
    libgpiod \
    libgpiod-tools \
    \
    \
    root-authorized-keys \
"

install_skel_files_for_root() {
    cp -a ${IMAGE_ROOTFS}${sysconfdir}/skel/. ${IMAGE_ROOTFS}/home/root/
}
ROOTFS_POSTPROCESS_COMMAND += "install_skel_files_for_root; "
