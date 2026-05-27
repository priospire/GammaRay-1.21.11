#!/usr/bin/env sh
set -eu

GRADLE_VERSION="9.5.0"
WRAPPER_DIR="${HOME}/.gradle/wrapper/dists/gradle-${GRADLE_VERSION}-bin"
GRADLE_HOME="${WRAPPER_DIR}/gradle-${GRADLE_VERSION}"
GRADLE_BIN="${GRADLE_HOME}/bin/gradle"
DIST_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
DIST_ZIP="${TMPDIR:-/tmp}/gradle-${GRADLE_VERSION}-bin.zip"

if [ ! -x "${GRADLE_BIN}" ]; then
  mkdir -p "${WRAPPER_DIR}"
  if [ ! -f "${DIST_ZIP}" ]; then
    if command -v curl >/dev/null 2>&1; then
      curl -L "${DIST_URL}" -o "${DIST_ZIP}"
    else
      wget "${DIST_URL}" -O "${DIST_ZIP}"
    fi
  fi
  unzip -o "${DIST_ZIP}" -d "${WRAPPER_DIR}"
fi

exec "${GRADLE_BIN}" "$@"
