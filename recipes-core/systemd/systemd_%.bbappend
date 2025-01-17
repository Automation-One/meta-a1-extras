
PACKAGECONFIG:remove = "${@'timesyncd' if d.getVar('NTP_PROVIDER','timesyncd') != 'timesyncd' else ''}"
