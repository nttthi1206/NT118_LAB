package com.nguyenthitrangthi.lab4.CAu1;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String studentId; // Mã sinh viên
    private String name;      // Tên sinh viên
    private String phone;     // Số điện thoại
    private String email;     // Email

    public Student() {}

    public Student(String studentId, String name, String phone, String email) {
        this.studentId = studentId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Student(int id, String studentId, String name, String phone, String email) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}