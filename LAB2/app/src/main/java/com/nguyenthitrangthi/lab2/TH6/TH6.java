package com.nguyenthitrangthi.lab2.TH6;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenthitrangthi.lab2.R;
import com.nguyenthitrangthi.lab2.TH5.Employee;

import java.util.ArrayList;

public class TH6 extends AppCompatActivity {

    private EditText etId, etFullName;
    private CheckBox cbManager;
    private RecyclerView rvEmployee;

    private ArrayList<Employee> employeeList;
    private EmployeeRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.th6);

        etId = findViewById(R.id.et_id);
        etFullName = findViewById(R.id.et_fullname);
        cbManager = findViewById(R.id.cb_manager);
        Button btnAdd = findViewById(R.id.btn_add);
        rvEmployee = findViewById(R.id.rv_employee);

        employeeList = new ArrayList<>();
        adapter = new EmployeeRecyclerAdapter(this, employeeList);

        // Thiết lập LayoutManager (dạng danh sách cuộn dọc)
        rvEmployee.setLayoutManager(new LinearLayoutManager(this));
        rvEmployee.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();
            String name = etFullName.getText().toString().trim();
            boolean isManager = cbManager.isChecked();

            if (!name.isEmpty()) {
                Employee emp = new Employee(id, name, isManager);
                employeeList.add(emp);

                // Thông báo thêm phần tử mới vào cuối danh sách
                adapter.notifyItemInserted(employeeList.size() - 1);
                rvEmployee.scrollToPosition(employeeList.size() - 1);

                // Reset các ô nhập liệu
                etId.setText("");
                etFullName.setText("");
                cbManager.setChecked(false);
                etId.requestFocus();
            }
        });
    }
}