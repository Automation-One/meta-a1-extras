inherit bundle

RAUC_BUNDLE_COMPATIBLE = "AutomationOne imx6 Gateway"

RAUC_BUNDLE_SLOTS ?= "rootfs"
RAUC_SLOT_rootfs ?= "a1-ubi-image"
RAUC_SLOT_rootfs[fstype] ?= "ubifs"
RAUC_BUNDLE_FORMAT ?= "verity"