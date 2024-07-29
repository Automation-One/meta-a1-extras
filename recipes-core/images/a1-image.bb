SUMMARY = "An image with various examples and demos"

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
  libgpiod \
  libgpiod-tools \
"
