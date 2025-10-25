-- Database Creation
CREATE DATABASE IF NOT EXISTS attendance_db;
USE attendance_db;

-- Students Table
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(20) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    department VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Attendance Records Table
CREATE TABLE IF NOT EXISTS attendance_records (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    date DATE NOT NULL,
    status ENUM('Present', 'Absent', 'Late') DEFAULT 'Absent',
    qr_code VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    UNIQUE KEY unique_student_date (student_id, date)
);

-- Insert Sample Data
INSERT INTO students (roll_number, full_name, email, phone, department, year) VALUES
('CS001', 'John Doe', 'john.doe@example.com', '1234567890', 'Computer Science', 2),
('CS002', 'Jane Smith', 'jane.smith@example.com', '0987654321', 'Computer Science', 2),
('EC001', 'Robert Johnson', 'robert.j@example.com', '1122334455', 'Electronics', 3),
('ME001', 'Emily Davis', 'emily.d@example.com', '2233445566', 'Mechanical', 1);

-- Create a user for the application (optional)
-- CREATE USER 'attendance_user'@'localhost' IDENTIFIED BY 'attendance_pass';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON attendance_db.* TO 'attendance_user'@'localhost';