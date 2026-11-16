package com.nguyenthitrangthi.lab2.TH4;

public class EmployeeFulltime extends Employee {
    @Override
    public double tinhLuong() {
        return 500.0; // Lương nhân viên chính thức cố định
    }

    @Override
    public String toString() {
        return super.toString() + " --> FullTime=" + tinhLuong();
    }
}