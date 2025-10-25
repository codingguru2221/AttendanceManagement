package com.attendance;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class AttendanceSystem {
    
    public static void main(String[] args) {
        System.out.println("=== Attendance Management System ===");
        
        try {
            // Test database connection
            Connection connection = DatabaseManager.getConnection();
            System.out.println("Connected to MySQL database successfully!");
            connection.close();
            
            // Display menu
            showMenu();
            
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
    }
    
    private static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. View All Students");
            System.out.println("2. Add New Student");
            System.out.println("3. Mark Attendance");
            System.out.println("4. View Attendance Records");
            System.out.println("5. Generate Attendance QR Code");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    viewAllStudents();
                    break;
                case 2:
                    addNewStudent(scanner);
                    break;
                case 3:
                    markAttendance(scanner);
                    break;
                case 4:
                    viewAttendanceRecords();
                    break;
                case 5:
                    generateQRCode(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
        
        scanner.close();
    }
    
    private static void viewAllStudents() {
        StudentService studentService = new StudentService();
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        
        System.out.println("\n--- All Students ---");
        System.out.printf("%-5s %-10s %-20s %-25s %-15s %-15s %-5s%n",
                "ID", "Roll No", "Full Name", "Email", "Phone", "Department", "Year");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        
        for (Student student : students) {
            System.out.printf("%-5d %-10s %-20s %-25s %-15s %-15s %-5d%n",
                    student.getStudentId(), student.getRollNumber(), student.getFullName(),
                    student.getEmail(), student.getPhone(), student.getDepartment(), student.getYear());
        }
    }
    
    private static void addNewStudent(Scanner scanner) {
        try {
            System.out.println("\n--- Add New Student ---");
            System.out.print("Enter Roll Number: ");
            String rollNumber = scanner.nextLine();
            
            System.out.print("Enter Full Name: ");
            String fullName = scanner.nextLine();
            
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();
            
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();
            
            System.out.print("Enter Year: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            Student student = new Student();
            student.setRollNumber(rollNumber);
            student.setFullName(fullName);
            student.setEmail(email);
            student.setPhone(phone);
            student.setDepartment(department);
            student.setYear(year);
            
            StudentService studentService = new StudentService();
            if (studentService.addStudent(student)) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Failed to add student.");
            }
        } catch (Exception e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }
    
    private static void markAttendance(Scanner scanner) {
        try {
            System.out.println("\n--- Mark Attendance ---");
            System.out.print("Enter Student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter Date (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine();
            
            System.out.print("Enter Status (Present/Absent/Late): ");
            String status = scanner.nextLine();
            
            AttendanceService attendanceService = new AttendanceService();
            if (attendanceService.markAttendance(studentId, dateStr, status)) {
                System.out.println("Attendance marked successfully!");
            } else {
                System.out.println("Failed to mark attendance.");
            }
        } catch (Exception e) {
            System.err.println("Error marking attendance: " + e.getMessage());
        }
    }
    
    private static void viewAttendanceRecords() {
        AttendanceService attendanceService = new AttendanceService();
        List<AttendanceRecord> records = attendanceService.getAllAttendanceRecords();
        
        if (records.isEmpty()) {
            System.out.println("No attendance records found!");
            return;
        }
        
        System.out.println("\n--- Attendance Records ---");
        System.out.printf("%-5s %-10s %-20s %-12s %-10s%n",
                "ID", "Roll No", "Full Name", "Date", "Status");
        System.out.println("------------------------------------------------------------------------");
        
        // We need to get student info for each record
        StudentService studentService = new StudentService();
        for (AttendanceRecord record : records) {
            Student student = studentService.getStudentById(record.getStudentId());
            String rollNumber = student != null ? student.getRollNumber() : "Unknown";
            String fullName = student != null ? student.getFullName() : "Unknown";
            
            System.out.printf("%-5d %-10s %-20s %-12s %-10s%n",
                    record.getRecordId(), rollNumber, fullName, record.getDate(), record.getStatus());
        }
    }
    
    private static void generateQRCode(Scanner scanner) {
        try {
            System.out.println("\n--- Generate Attendance QR Code ---");
            System.out.print("Enter Student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter Date (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine();
            
            System.out.print("Enter file path to save QR code (e.g., qr_codes/attendance.png): ");
            String filePath = scanner.nextLine();
            
            AttendanceService attendanceService = new AttendanceService();
            if (attendanceService.generateAttendanceQRCode(studentId, dateStr, filePath)) {
                System.out.println("QR Code generated successfully!");
            } else {
                System.out.println("Failed to generate QR code.");
            }
        } catch (Exception e) {
            System.err.println("Error generating QR code: " + e.getMessage());
        }
    }
}