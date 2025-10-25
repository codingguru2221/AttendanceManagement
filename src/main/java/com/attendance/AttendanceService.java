package com.attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for attendance-related operations
 */
public class AttendanceService {
    
    /**
     * Mark attendance for a student
     * @param studentId Student ID
     * @param date Attendance date
     * @param status Attendance status (Present, Absent, Late)
     * @return true if successful, false otherwise
     */
    public boolean markAttendance(int studentId, String date, String status) {
        // Check if student exists
        StudentService studentService = new StudentService();
        if (studentService.getStudentById(studentId) == null) {
            System.err.println("Student with ID " + studentId + " does not exist!");
            return false;
        }
        
        // Insert or update attendance record
        String query = "INSERT INTO attendance_records (student_id, date, status) VALUES (?, ?, ?) " +
                      "ON DUPLICATE KEY UPDATE status = VALUES(status)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, studentId);
            stmt.setString(2, date);
            stmt.setString(3, status);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error marking attendance: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get attendance records for a specific student
     * @param studentId Student ID
     * @return List of attendance records
     */
    public List<AttendanceRecord> getAttendanceByStudent(int studentId) {
        List<AttendanceRecord> records = new ArrayList<>();
        String query = "SELECT ar.*, s.roll_number, s.full_name " +
                      "FROM attendance_records ar " +
                      "JOIN students s ON ar.student_id = s.student_id " +
                      "WHERE ar.student_id = ? " +
                      "ORDER BY ar.date DESC";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AttendanceRecord record = new AttendanceRecord();
                    record.setRecordId(rs.getInt("record_id"));
                    record.setStudentId(rs.getInt("student_id"));
                    record.setDate(rs.getDate("date"));
                    record.setStatus(rs.getString("status"));
                    record.setQrCode(rs.getString("qr_code"));
                    
                    records.add(record);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching attendance records: " + e.getMessage());
            e.printStackTrace();
        }
        
        return records;
    }
    
    /**
     * Get all attendance records
     * @return List of all attendance records
     */
    public List<AttendanceRecord> getAllAttendanceRecords() {
        List<AttendanceRecord> records = new ArrayList<>();
        String query = "SELECT ar.*, s.roll_number, s.full_name " +
                      "FROM attendance_records ar " +
                      "JOIN students s ON ar.student_id = s.student_id " +
                      "ORDER BY ar.date DESC, s.roll_number";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                AttendanceRecord record = new AttendanceRecord();
                record.setRecordId(rs.getInt("record_id"));
                record.setStudentId(rs.getInt("student_id"));
                record.setDate(rs.getDate("date"));
                record.setStatus(rs.getString("status"));
                record.setQrCode(rs.getString("qr_code"));
                
                records.add(record);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching attendance records: " + e.getMessage());
            e.printStackTrace();
        }
        
        return records;
    }
    
    /**
     * Generate QR code for attendance
     * @param studentId Student ID
     * @param date Attendance date
     * @param filePath Path to save QR code image
     * @return true if successful, false otherwise
     */
    public boolean generateAttendanceQRCode(int studentId, String date, String filePath) {
        try {
            // Check if student exists
            StudentService studentService = new StudentService();
            Student student = studentService.getStudentById(studentId);
            if (student == null) {
                System.err.println("Student with ID " + studentId + " does not exist!");
                return false;
            }
            
            // Generate QR code data
            String qrData = QRCodeUtil.generateAttendanceQRData(studentId, date);
            
            // Generate QR code
            QRCodeUtil.generateQRCode(qrData, filePath);
            
            // Save QR code path to database
            String query = "INSERT INTO attendance_records (student_id, date, status, qr_code) VALUES (?, ?, ?, ?) " +
                          "ON DUPLICATE KEY UPDATE qr_code = VALUES(qr_code)";
            
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                
                stmt.setInt(1, studentId);
                stmt.setString(2, date);
                stmt.setString(3, "Pending"); // Default status
                stmt.setString(4, filePath);
                
                stmt.executeUpdate();
            }
            
            return true;
        } catch (Exception e) {
            System.err.println("Error generating QR code: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}