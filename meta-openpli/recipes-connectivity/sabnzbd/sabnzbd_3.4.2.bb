SUMMARY = "SABnzbd - The automated Usenet download tool"
DESCRIPTION = "SABnzbd is an Open Source Binary Newsreader written in Python."
HOMEPAGE = "https://sabnzbd.org"
MAINTAINER = "team@sabnzbd.org"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYRIGHT.txt;md5=d4bfc20f13bd0e7363c57525136734ee"

DEPENDS = "${PYTHON_PN}"
RDEPENDS_${PN} = "\
    ${PYTHON_PN}-cheetah ${PYTHON_PN}-compression ${PYTHON_PN}-core ${PYTHON_PN}-crypt ${PYTHON_PN}-ctypes ${PYTHON_PN}-email ${PYTHON_PN}-html \
    ${PYTHON_PN}-misc ${PYTHON_PN}-multiprocessing ${PYTHON_PN}-sqlite3 ${PYTHON_PN}-shell ${PYTHON_PN}-sabyenc3 ${PYTHON_PN}-configobj \
    ${PYTHON_PN}-cryptography ${PYTHON_PN}-feedparser ${PYTHON_PN}-cheroot ${PYTHON_PN}-cherrypy ${PYTHON_PN}-portend ${PYTHON_PN}-chardet \
    ${PYTHON_PN}-notify2 ${PYTHON_PN}-puremagic ${PYTHON_PN}-guessit ${PYTHON_PN}-sgmllib3k ${PYTHON_PN}-more-itertools ${PYTHON_PN}-modules \
    "

RRECOMMENDS_${PN} = "par2cmdline unrar"

SRC_URI = "https://github.com/sabnzbd/sabnzbd/releases/download/3.4.2/SABnzbd-3.4.2-src.tar.gz \
	file://sabnzbd \
	file://sabnzbd.conf \
	file://init-functions \
	"

SRC_URI[md5sum] = "02f7c62bbae19dc43f07627281268641"
SRC_URI[sha256sum] = "fd2c91e15aa667657dd7c960872fbff148467e88fe2d8c8e6e81fcc2e3402842"

S = "${WORKDIR}/SABnzbd-${PV}"

INSTALLDIR = "${libdir}/${PN}"

PACKAGES = "${PN}-doc ${PN}"

FILES_${PN}-doc = "${INSTALLDIR}/*.txt ${INSTALLDIR}/licenses ${INSTALLDIR}/interfaces/*/licenses"
FILES_${PN} = "${INSTALLDIR} /etc/init.d/sabnzbd /etc/init.d/init-functions /etc/enigma2/sabnzbd.conf"

inherit update-rc.d
INITSCRIPT_NAME = "sabnzbd"
INITSCRIPT_PARAMS = "defaults"

do_install() {
	install -d ${D}${INSTALLDIR}
	cp -r . ${D}${INSTALLDIR}/
	rm -rf ${D}${INSTALLDIR}/.git
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/sabnzbd ${D}${sysconfdir}/init.d/sabnzbd
	install -m 755 ${WORKDIR}/init-functions ${D}${sysconfdir}/init.d/init-functions
	install -d ${D}${sysconfdir}/enigma2
	install -m 644 ${WORKDIR}/sabnzbd.conf ${D}/etc/enigma2/sabnzbd.conf
}