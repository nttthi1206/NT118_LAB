package com.nguyenthitrangthi.lab2.TH5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenthitrangthi.lab2.R;

import java.util.ArrayList;

public class TH5 extends AppCompatActivity {

    private EditText etId, etFullName;
    private CheckBox cbManager;
    private ListView lvEmployee;

    private ArrayList<Employee> employeeList;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.th5);

        etId = findViewById(R.id.et_id);
        etFullName = findViewById(R.id.et_fullname);
        cbManager = findViewById(R.id.cb_manager);
        Button btnAdd = findViewById(R.id.btn_add);
        lvEmployee = findViewById(R.id.lv_employee);

        employeeList = new ArrayList<>();
        adapter = new EmployeeAdapter(this, R.layout.item_employee, employeeList);
        lvEmployee.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();
            String name = etFullName.getText().toString().trim();
            boolean isManager = cbManager.isChecked();

            if (!name.isEmpty()) {
                Employee emp = new Employee(id, name, isManager);
                employeeList.add(emp);
                adapter.notifyDataSetChanged();

                // Reset form nhập
                etId.setText("");
                etFullName.setText("");
                cbManager.setChecked(false);
                etId.requestFocus();
            }
        });
    }
}