# Project Structure

## Directory Structure

```
AttendanceManagement/
├── .idea/                    # IntelliJ IDEA configuration
│   └── runConfigurations/    # Run configurations
├── qr_codes/                 # Generated QR codes (created at runtime)
├── src/                      # Source code
│   └── main/
│       └── java/
│           └── com/
│               └── attendance/
│                   ├── AttendanceSystem.java     # Main application class
│                   ├── DatabaseManager.java      # Database connection management
│                   ├── Student.java              # Student entity class
│                   ├── AttendanceRecord.java     # Attendance record entity class
│                   ├── StudentService.java       # Student-related operations
│                   ├── AttendanceService.java    # Attendance-related operations
│                   └── QRCodeUtil.java           # QR code generation utilities
├── target/                   # Build output (created by Maven)
│   ├── classes/              # Compiled classes
│   ├── lib/                  # Dependencies
│   └── AttendanceManagement-1.0-SNAPSHOT.jar  # Executable JAR
├── database_schema.sql       # Database schema definition
├── pom.xml                   # Maven configuration
├── README.md                 # Project documentation
├── PROJECT_STRUCTURE.md      # This file
├── .gitignore                # Git ignore rules
├── init_project.bat          # Project initialization script
├── build.bat                 # Build script
├── run.bat                   # Run script
└── setup_db.bat              # Database setup script
```

## Class Descriptions

### AttendanceSystem.java
The main application class that provides the command-line interface for the attendance system.

### DatabaseManager.java
Handles database connections and provides utility methods for managing connections.

### Student.java
Entity class representing a student with properties like ID, roll number, name, etc.

### AttendanceRecord.java
Entity class representing an attendance record with properties like record ID, student ID, date, status, etc.

### StudentService.java
Service class that handles all student-related operations including CRUD operations.

### AttendanceService.java
Service class that handles all attendance-related operations including marking attendance and generating QR codes.

### QRCodeUtil.java
Utility class for generating QR codes using the ZXing library.

## Scripts

### init_project.bat
Initializes the project by checking dependencies and building the application.

### build.bat
Builds the project using Maven.

### run.bat
Runs the application from the packaged JAR file.

### setup_db.bat
Sets up the database by executing the schema SQL file.

## Configuration Files

### pom.xml
Maven configuration file that defines project dependencies and build settings.

### database_schema.sql
SQL script that creates the database schema and sample data.

## IntelliJ IDEA Configuration

The project includes IntelliJ IDEA run configurations for easy execution directly from the IDE.