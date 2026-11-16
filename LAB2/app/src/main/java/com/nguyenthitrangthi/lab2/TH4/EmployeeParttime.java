package com.nguyenthitrangthi.lab2.TH4;

public class EmployeeParttime extends Employee {
    @Override
    public double tinhLuong() {
        return 150.0; // Lương nhân viên thời vụ cố định
    }

    @Override
    public String toString() {
        return super.toString() + " --> PartTime=" + tinhLuong();
    }
}