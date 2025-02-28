
IMAGE_INSTALL:append = "${@'python3 python3-paramiko' if d.getVar('INCLUDE_PYTHON') else ''}"
