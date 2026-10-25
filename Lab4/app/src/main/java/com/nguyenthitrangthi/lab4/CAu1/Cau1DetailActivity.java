package com.nguyenthitrangthi.lab4.CAu1; // Thay bằng package của bạn

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.nguyenthitrangthi.lab4.R;

public class Cau1DetailActivity extends AppCompatActivity {
    private EditText etCode, etName, etPhone, etEmail;
    private Button btnUpdate, btnDelete;
    private Student currentStudent;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Nạp đúng layout cau1_detail.xml
        setContentView(R.layout.cau1_detail);

        etCode = findViewById(R.id.et_detail_code);
        etName = findViewById(R.id.et_detail_name);
        etPhone = findViewById(R.id.et_detail_phone);
        etEmail = findViewById(R.id.et_detail_email);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        db = new DatabaseHandler(this);

        // Nhận dữ liệu truyền sang từ Cau1Activity
        currentStudent = (Student) getIntent().getSerializableExtra("student_data");

        if (currentStudent != null) {
            etCode.setText(currentStudent.getStudentId());
            etName.setText(currentStudent.getName());
            etPhone.setText(currentStudent.getPhone());
            etEmail.setText(currentStudent.getEmail());
        }

        // Cập nhật thông tin sinh viên
        btnUpdate.setOnClickListener(v -> {
            currentStudent.setStudentId(etCode.getText().toString().trim());
            currentStudent.setName(etName.getText().toString().trim());
            currentStudent.setPhone(etPhone.getText().toString().trim());
            currentStudent.setEmail(etEmail.getText().toString().trim());

            db.updateStudent(currentStudent);
            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity để quay lại màn hình danh sách
        });

        // Xóa sinh viên
        btnDelete.setOnClickListener(v -> {
            db.deleteStudent(currentStudent.getId());
            Toast.makeText(this, "Đã xóa sinh viên!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}