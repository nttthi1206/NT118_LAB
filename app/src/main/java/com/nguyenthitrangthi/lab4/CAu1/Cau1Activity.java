package com.nguyenthitrangthi.lab4.CAu1; // Thay bằng package của bạn (ví dụ: package com.nguyenthitrangthi.lab3;)

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nguyenthitrangthi.lab4.R;
import java.util.List;

public class Cau1Activity extends AppCompatActivity {
    private EditText etCode, etName, etPhone, etEmail;
    private Button btnAdd;
    private RecyclerView rvStudents;
    private DatabaseHandler db;
    private List<Student> studentList;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Nạp đúng layout cau1_main.xml
        setContentView(R.layout.cau1_main);

        etCode = findViewById(R.id.et_code);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        btnAdd = findViewById(R.id.btn_add);
        rvStudents = findViewById(R.id.rv_students);

        db = new DatabaseHandler(this);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));

        loadData();

        // Xử lý thêm sinh viên
        btnAdd.setOnClickListener(v -> {
            String code = etCode.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if (code.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập MSSV và Tên", Toast.LENGTH_SHORT).show();
                return;
            }

            db.addStudent(new Student(code, name, phone, email));
            Toast.makeText(this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();

            // Xóa trắng form
            etCode.setText("");
            etName.setText("");
            etPhone.setText("");
            etEmail.setText("");

            loadData();
        });
    }

    private void loadData() {
        studentList = db.getAllStudents();
        adapter = new StudentAdapter(studentList, new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student student) {
                // Nhấn vào 1 dòng -> Chuyển sang Cau1DetailActivity
                Intent intent = new Intent(Cau1Activity.this, Cau1DetailActivity.class);
                intent.putExtra("student_data", student);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(Student student) {
                // Nhấn giữ lâu -> Hộp thoại xác nhận xóa
                new AlertDialog.Builder(Cau1Activity.this)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc muốn xóa sinh viên " + student.getName() + "?")
                        .setPositiveButton("Xóa", (dialog, which) -> {
                            db.deleteStudent(student.getId());
                            Toast.makeText(Cau1Activity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                            loadData();
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
            }
        });
        rvStudents.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load lại danh sách khi quay trở về từ màn hình Chi tiết (đã sửa hoặc xóa)
        loadData();
    }
}