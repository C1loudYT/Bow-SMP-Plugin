@echo off
REM Build script for Bow SMP Plugin

echo Building BowSMPPlugin...
mvn clean package

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ============================================
    echo Build successful!
    echo JAR file location: target\BowSMPPlugin-1.0.0.jar
    echo ============================================
    pause
) else (
    echo.
    echo Build failed! Make sure Maven is installed.
    echo Download Maven: https://maven.apache.org/download.cgi
    pause
)
