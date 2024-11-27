test -n "$BOOT_SYSTEM" || setenv BOOT_SYSTEM "A"

test -n "${default_bootargs}" || setenv default_bootargs "console=ttymxc3,115200 rootwait ro ubi.mtd=0 init=/sbin/preinit"



if test "${BOOT_SYSTEM}" = "A"; then
  echo "Booting from rootfsA"
  setenv BOOT_PARTITION "rootfsA"
elif test "${BOOT_SYSTEM}" = "B"; then
  echo "Booting from rootfsB"
  setenv BOOT_PARTITION "rootfsB"
else
  echo "WARNING: Unknown BOOT_SYSTEM value: ${BOOT_SYSTEM}"
  echo "Using default BOOT_PARTITION=rootfsA"
  setenv BOOT_PARTITION "rootfsA"
fi

echo "Loading kernel"
ubi part UBI
ubifsmount ubi0:${BOOT_PARTITION}
ubifsload ${loadaddr} /boot/fitImage

echo " Starting kernel"
setenv bootargs "${default_bootargs} root=ubi0:${BOOT_PARTITION} rootfstype=ubifs"
bootm ${loadaddr}