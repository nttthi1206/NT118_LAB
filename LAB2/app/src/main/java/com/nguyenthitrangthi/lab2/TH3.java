package com.nguyenthitrangthi.lab2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TH3 extends AppCompatActivity {

    EditText etName;
    Button btnSubmit;
    TextView tvSelection;
    ListView lvPerson;

    ArrayList<String> names;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.th3);

        // Ánh xạ các control
        etName = findViewById(R.id.et_name);
        btnSubmit = findViewById(R.id.btn_submit);
        tvSelection = findViewById(R.id.tv_selection);
        lvPerson = findViewById(R.id.lv_person);

        // 1. Tạo ArrayList object
        names = new ArrayList<>();

        // Thêm vài dữ liệu mẫu ban đầu
        names.add("teo");
        names.add("ty");
        names.add("bin");

        // Khởi tạo Custom Adapter hỗ trợ tô đậm phần tử khi người dùng nhập tên đã có sẵn
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);

                String currentItem = getItem(position);
                String query = etName.getText().toString().trim();

                if (!query.isEmpty() && currentItem != null && currentItem.equalsIgnoreCase(query)) {
                    // Khớp chính xác hoàn toàn (ví dụ: gõ "ty" trùng với "ty"): tô đậm chữ và đổi màu nền nổi bật
                    textView.setTypeface(null, Typeface.BOLD);
                    textView.setTextColor(Color.parseColor("#1976D2")); // Màu chữ xanh đậm
                    view.setBackgroundColor(Color.parseColor("#E3F2FD")); // Màu nền xanh nhạt
                } else if (!query.isEmpty() && currentItem != null && currentItem.toLowerCase().contains(query.toLowerCase())) {
                    // Khớp một phần (ví dụ: gõ "t" chứa trong "teo", "ty"): tô đậm chữ
                    textView.setTypeface(null, Typeface.BOLD);
                    textView.setTextColor(Color.parseColor("#0D47A1"));
                    view.setBackgroundColor(Color.parseColor("#BBDEFB"));
                } else {
                    // Mặc định: chữ thường và nền trong suốt
                    textView.setTypeface(null, Typeface.NORMAL);
                    textView.setTextColor(Color.BLACK);
                    view.setBackgroundColor(Color.TRANSPARENT);
                }

                return view;
            }
        };

        lvPerson.setAdapter(adapter);

        // Lắng nghe thay đổi văn bản trong ô etName để cập nhật tô đậm danh sách ngay lập tức
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // 4. Xử lý sự kiện nhấn nút Nhập
        btnSubmit.setOnClickListener(v -> {
            String inputName = etName.getText().toString().trim();
            if (!inputName.isEmpty()) {
                // Thêm dữ liệu mới vào ArrayList
                names.add(inputName);

                // Cập nhật dữ liệu mới lên giao diện
                adapter.notifyDataSetChanged();

                // Reset ô nhập
                etName.setText("");
                etName.requestFocus();
            }
        });

        // 5. Xử lý sự kiện chọn một phần tử trong ListView (Click thường)
        lvPerson.setOnItemClickListener((parent, view, position, id) -> {
            // Hiển thị vị trí và giá trị lên TextView
            String text = "position: " + position + " ; value = " + names.get(position);
            tvSelection.setText(text);
        });

        // 6. Xử lý sự kiện Long click (Nhấn giữ lâu để xóa)
        lvPerson.setOnItemLongClickListener((parent, view, position, id) -> {
            // Xóa phần tử tại vị trí position khỏi ArrayList
            names.remove(position);

            // Cập nhật lại giao diện ListView
            adapter.notifyDataSetChanged();

            String text = "Đã xóa phần tử tại vị trí " + position;
            tvSelection.setText(text);

            return true; // Trả về true để báo sự kiện long click đã được xử lý xong
        });
    }
}