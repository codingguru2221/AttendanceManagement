# Attendance Management System

A Java-based attendance management system using MySQL database with QR code functionality.

## Features

- Student management (add, view, update, delete)
- Attendance tracking with status (Present, Absent, Late)
- QR code generation for attendance
- MySQL database integration
- Command-line interface

## Prerequisites

- Java 11 or higher
- MySQL Server
- Maven

## Project Structure

See [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md) for detailed information about the project organization.

## Database Setup

1. Create a MySQL database:
   ```sql
   CREATE DATABASE attendance_db;
   ```

2. Execute the database schema:
   ```bash
   # On Windows
   setup_db.bat
   
   # Or manually
   mysql -u root -p attendance_db < database_schema.sql
   ```

3. Update the database credentials in [DatabaseManager.java](src/main/java/com/attendance/DatabaseManager.java) if needed:
   ```java
   private static final String USERNAME = "root";
   private static final String PASSWORD = "your_password"; // <-- Update this
   ```

## Building the Project

### Using Maven (Recommended)

```bash
# Compile and package the project
mvn clean package

# Run the application
java -jar target/AttendanceManagement-1.0-SNAPSHOT.jar
```

### Using Build Scripts (Windows)

```bash
# Initialize and build the project
init_project.bat

# Build the project
build.bat

# Run the application
run.bat
```

## IntelliJ IDEA Setup

1. Open IntelliJ IDEA
2. Select "Open" and choose the project directory
3. IntelliJ should automatically detect this as a Maven project
4. Let IntelliJ import the Maven dependencies
5. Run the application using the "Attendance System" run configuration

## Usage

1. Run the application using one of the methods above
2. Use the menu to navigate through different options:
   - View all students
   - Add new students
   - Mark attendance
   - View attendance records
   - Generate QR codes for attendance

## Dependencies

- MySQL Connector/J 8.0.33
- Google ZXing (QR Code library) 3.5.1

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running
- Verify database credentials in [DatabaseManager.java](src/main/java/com/attendance/DatabaseManager.java)
- Check that the `attendance_db` database exists

### QR Code Generation Issues
- Ensure the `qr_codes` directory exists in the project root
- Check that you have write permissions to the directory

## License

This project is licensed under the MIT License.