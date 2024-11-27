SUMMARY = "An image with various examples and demos tailored specifically for the AutomationOne Gateways"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL:append = " \
  modemmanager \
  networkmanager \
  networkmanager-nmcli \
  networkmanager-ppp \
  networkmanager-wifi \
  networkmanager-wwan \
  iptables \
  \
  python3 \
  python3-pip \
  python3-paramiko \
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
"
