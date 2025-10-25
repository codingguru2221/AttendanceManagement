@echo off
echo Starting Attendance Management System...

REM Check if target JAR exists
if not exist "target\AttendanceManagement-1.0-SNAPSHOT.jar" (
    echo JAR file not found. Building the project...
    call mvn package
    if %errorlevel% neq 0 (
        echo Build failed!
        pause
        exit /b 1
    )
)

REM Run the application
java -jar target/AttendanceManagement-1.0-SNAPSHOT.jar

if %errorlevel% neq 0 (
    echo Failed to start the application!
    pause
    exit /b 1
)

pause