SUMMARY = "An image with various examples and demos"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL:append = " \
  modemmanager \
  networkmanager \
  networkmanager-nmcli \
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
"
