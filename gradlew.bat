@echo off
setlocal

set "GRADLE_VERSION=9.5.0"
set "WRAPPER_DIR=%USERPROFILE%\.gradle\wrapper\dists\gradle-%GRADLE_VERSION%-bin"
set "GRADLE_HOME=%WRAPPER_DIR%\gradle-%GRADLE_VERSION%"
set "GRADLE_BIN=%GRADLE_HOME%\bin\gradle.bat"
set "DIST_URL=https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip"
set "DIST_ZIP=%TEMP%\gradle-%GRADLE_VERSION%-bin.zip"

if not exist "%GRADLE_BIN%" (
  powershell -NoProfile -ExecutionPolicy Bypass -Command "if (!(Test-Path '%WRAPPER_DIR%')) { New-Item -ItemType Directory -Force -Path '%WRAPPER_DIR%' | Out-Null }; if (!(Test-Path '%DIST_ZIP%')) { Invoke-WebRequest '%DIST_URL%' -OutFile '%DIST_ZIP%' }; Expand-Archive -Force '%DIST_ZIP%' '%WRAPPER_DIR%'"
)

call "%GRADLE_BIN%" %*
exit /b %ERRORLEVEL%
