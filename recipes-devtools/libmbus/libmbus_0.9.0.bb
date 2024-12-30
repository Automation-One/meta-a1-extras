DESCRIPTION = "Installes the libmbus library"
DEPENDS = ""
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://COPYING;md5=56be74e48c662fda2b5bb14edaf21ca0"


SRC_URI = "git://github.com/rscada/libmbus.git;protocol=https;branch=master"

SRCREV="refs/tags/${PV}"

S = "${WORKDIR}/git"

DEPENDS = "libtool perl-native"

inherit autotools

do_configure:prepend() {
    # Create the directory ${S}/libltdl/config to prevent the configure step from failing the first time this is run,
    # as this folder is created too late for some unknown reason.
    mkdir -p ${S}/libltdl/config
}
