SUMMARY = "Kodi Media Center PVR plugins"

PV = "3.4.10+git${SRCPV}"
PKGV = "3.4.10+git${GITPKGV}"

KODIADDONPLUGIN = "stalker"

DEPENDS_append = "nlohmann-json"

require kodi-addon-pvr.inc
