DESCRIPTON = "Installes The Automation One Suite"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=9a7ad967da5d8884afe6d586bba69c4e"

SRC_URI = "\
  git://git@github.com:/Automation-One/a1-iot-suite.git;protocol=https;tag=v${PV};branch=master \
  file://AutomationOne.service \
"

inherit systemd setuptools3-base

SYSTEMD_SERVICE:${PN} = "AutomationOne.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

A1PATH ?= "/home/root/AutomationOne"

FILES:${PN} += "${A1PATH}/*"

do_install() {
  install -Dm 0755 ${WORKDIR}/git/AutomationOne.py ${D}${bindir}/AutomationOne

  install -d ${D}${PYTHON_SITEPACKAGES_DIR}
  cp -R --no-dereference --preserve=mode,links -v ${WORKDIR}/git/AutomationOne ${D}${PYTHON_SITEPACKAGES_DIR}/AutomationOne

  install -Dm 0644 ${WORKDIR}/AutomationOne.service ${D}${sysconfdir}/systemd/system/AutomationOne.service

  install -Dm 0755 ${WORKDIR}/git/Utils/modbus.py ${D}${bindir}/modbus

  mkdir -p ${D}${A1PATH}
  cp -R --no-dereference --preserve=mode,links -v ${WORKDIR}/git/DemoConfigs ${D}${A1PATH}/DemoConfigs

  ln -s ${A1PATH}/DemoConfigs/DemoConfig.yaml ${D}${A1PATH}/config.yaml
}

RDEPENDS:${PN} += "\
  python3-core \
  python3-logging  \
  python3-paho-mqtt \
  python3-pymodbus \
  python3-pyyaml \
  python3-timeloop \
  python3-json \
  \
  python3-mbus \
  python3-xmltodict \
"