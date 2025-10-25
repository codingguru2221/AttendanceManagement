@echo off
echo Initializing Attendance Management System...

REM Check if Maven is installed
echo Checking for Maven...
mvn -v >nul 2>&1
if %errorlevel% neq 0 (
    echo Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

REM Check if MySQL is installed
echo Checking for MySQL...
mysql --version >nul 2>&1
if %errorlevel% neq 0 (
    echo MySQL is not installed or not in PATH
    echo Please install MySQL and try again
    pause
    exit /b 1
)

REM Build the project
echo Building the project...
call mvn clean package
if %errorlevel% neq 0 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Project setup completed successfully!
echo.
echo Next steps:
echo 1. Run setup_db.bat to create the database
echo 2. Update DatabaseManager.java with your MySQL credentials if needed
echo 3. Run run.bat to start the application
echo.

pause