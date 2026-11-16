package com.nguyenthitrangthi.lab2.TH4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenthitrangthi.lab2.R;

import java.util.ArrayList;

public class TH4 extends AppCompatActivity {

    EditText etId, etName;
    RadioGroup rgType;
    Button btnNhap;
    ListView lvEmployee;

    ArrayList<Employee> employees;
    ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.th4);

        // Ánh xạ các control từ XML sang Java
        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        rgType = findViewById(R.id.rg_type);
        btnNhap = findViewById(R.id.btn_nhap);
        lvEmployee = findViewById(R.id.lv_employee);

        // Khởi tạo ArrayList và Adapter
        employees = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                employees
        );
        lvEmployee.setAdapter(adapter);

        // Bắt sự kiện khi bấm nút "Nhập NV"
        btnNhap.setOnClickListener(v -> addNewEmployee());
    }

    // Hàm xử lý thêm nhân viên mới
    public void addNewEmployee() {
        int radId = rgType.getCheckedRadioButtonId();
        String id = etId.getText().toString().trim();
        String name = etName.getText().toString().trim();

        Employee employee;

        // Kiểm tra xem chọn Chính thức hay Thời vụ
        if (radId == R.id.rd_chinhthuc) {
            employee = new EmployeeFulltime();
        } else {
            employee = new EmployeeParttime();
        }

        // Gán thông tin chung
        employee.setId(id);
        employee.setName(name);

        // Đưa vào danh sách ArrayList
        employees.add(employee);

        // Cập nhật lại giao diện ListView
        adapter.notifyDataSetChanged();

        // Xóa trắng ô nhập sau khi thêm
        etId.setText("");
        etName.setText("");
        etId.requestFocus();
    }
}