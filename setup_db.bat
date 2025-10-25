@echo off
echo Setting up Attendance Management System Database...

REM Check if MySQL is installed
mysql --version >nul 2>&1
if %errorlevel% neq 0 (
    echo MySQL is not installed or not in PATH
    echo Please install MySQL and try again
    pause
    exit /b 1
)

REM Create database and tables
echo Creating database and tables...
mysql -u root -p < database_schema.sql

if %errorlevel% equ 0 (
    echo Database setup completed successfully!
) else (
    echo Database setup failed!
    pause
    exit /b 1
)

pause