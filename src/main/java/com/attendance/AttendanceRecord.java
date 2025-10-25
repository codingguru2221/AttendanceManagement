package com.attendance;

import java.util.Date;

/**
 * Attendance Record entity class
 */
public class AttendanceRecord {
    private int recordId;
    private int studentId;
    private Date date;
    private String status; // Present, Absent, Late
    private String qrCode;
    private Date createdAt;
    
    // Default constructor
    public AttendanceRecord() {}
    
    // Constructor with all fields
    public AttendanceRecord(int recordId, int studentId, Date date, String status, String qrCode) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.qrCode = qrCode;
    }
    
    // Getters and Setters
    public int getRecordId() {
        return recordId;
    }
    
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getQrCode() {
        return qrCode;
    }
    
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "recordId=" + recordId +
                ", studentId=" + studentId +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }
}