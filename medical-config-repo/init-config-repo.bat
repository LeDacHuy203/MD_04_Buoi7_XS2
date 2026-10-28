@echo off
echo ===================================================
echo Khoi tao Git repository cho medical-config-repo...
echo ===================================================
cd /d "%~dp0"
git init
git config user.name "Medical Admin"
git config user.email "admin@medical.local"
git add .
git commit -m "Initial commit: medical configuration files"
git branch -M main
echo.
echo ===================================================
echo Khoi tao hoan tat! Git repo da san sang.
echo ===================================================
pause
