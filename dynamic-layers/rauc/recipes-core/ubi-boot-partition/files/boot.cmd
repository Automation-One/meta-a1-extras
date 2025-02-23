
test -n "${BOOT_ORDER}" || setenv BOOT_ORDER "A B"
test -n "${BOOT_A_LEFT}" || setenv BOOT_A_LEFT 3
test -n "${BOOT_B_LEFT}" || setenv BOOT_B_LEFT 3

test -n "${default_bootargs}" || setenv default_bootargs "console=ttymxc3,115200 rootwait ro ubi.mtd=0 init=/sbin/preinit"
setenv bootargs

ubi part UBI

for BOOT_SLOT in "${BOOT_ORDER}"; do
  if test "x${bootargs}" != "x"; then
    # skip remaining slots
  elif test "x${BOOT_SLOT}" = "xA"; then
    if test 0x${BOOT_A_LEFT} -gt 0; then
      echo "Found valid slot A, ${BOOT_A_LEFT} attempts remaining"
      setexpr BOOT_A_LEFT ${BOOT_A_LEFT} - 1
      setenv load_kernel "ubifsmount ubi0:rootfsA; ubifsload ${loadaddr} /boot/fitImage"
      setenv bootargs "${default_bootargs} root=ubi0:rootfsA rootfstype=ubifs rauc.slot=A"
    fi
  elif test "x${BOOT_SLOT}" = "xB"; then
    if test 0x${BOOT_B_LEFT} -gt 0; then
      echo "Found valid slot B, ${BOOT_B_LEFT} attempts remaining"
      setexpr BOOT_B_LEFT ${BOOT_B_LEFT} - 1
      setenv load_kernel "ubifsmount ubi0:rootfsB; ubifsload ${loadaddr} /boot/fitImage"
      setenv bootargs "${default_bootargs} root=ubi0:rootfsB rootfstype=ubifs rauc.slot=B"
    fi
  fi
done

if test -n "${bootargs}"; then
  saveenv
else
  echo "No valid slot found, resetting tries to 3"
  setenv BOOT_A_LEFT 3
  setenv BOOT_B_LEFT 3
  saveenv
  reset
fi

echo "Loading kernel"
run load_kernel
echo " Starting kernel"
bootm ${loadaddr}