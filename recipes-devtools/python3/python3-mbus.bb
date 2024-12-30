SUMMARY = "This is a python wrapper around libmbus"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "git://github.com/rscada/python-mbus.git;protocol=https;branch=master"

SRCREV = "${AUTOREV}"
BPV = "0.1.0"
PV = "${BPV}+gitr${SRCPV}"


S = "${WORKDIR}/git"

inherit  setuptools3

RDEPENDS:${PN} += "\
  libmbus  \
  python3-ctypes \
"
