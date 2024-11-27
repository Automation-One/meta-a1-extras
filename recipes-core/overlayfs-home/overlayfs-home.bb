
SUMMARY = "OverlayFS for /home directory"
DESCRIPTION = "This recipe sets up OverlayFS for the /home directory."
LICENSE = "MIT"

inherit overlayfs
OVERLAYFS_WRITABLE_PATHS[data] += "/home"