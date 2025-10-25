@echo off
echo Building Attendance Management System...

REM Check if Maven is installed
mvn -v >nul 2>&1
if %errorlevel% neq 0 (
    echo Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

REM Clean and compile the project
mvn clean compile

if %errorlevel% equ 0 (
    echo Build successful!
    echo You can now run the application using: mvn exec:java -Dexec.mainClass="com.attendance.AttendanceSystem"
) else (
    echo Build failed!
    pause
    exit /b 1
)

pause