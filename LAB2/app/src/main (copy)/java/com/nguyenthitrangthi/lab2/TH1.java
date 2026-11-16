package com.nguyenthitrangthi.lab2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TH1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.th1);


        // Bước 1: Khởi tạo đối tượng ListView và TextView từ file XML
        ListView lvPerson = findViewById(R.id.lv_person);
        TextView tvSelection = findViewById(R.id.tv_selection);

        // Bước 2: Khởi tạo mảng chứa dữ liệu định sẵn
        final String[] arr = {"Tèo", "Tý", "Bin", "Bo"};

        // Bước 3: Xây dựng Adapter để đổ dữ liệu vào từng dòng
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        );

        // Bước 4: Gắn Adapter cho ListView
        lvPerson.setAdapter(adapter);

        // Bước 5: Xử lý sự kiện khi bấm (click) vào 1 dòng trên ListView
        lvPerson.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            // position chính là vị trí của phần tử được bấm (bắt đầu từ 0)
            String text = "position: " + position + " ; value = " + arr[position];
            tvSelection.setText(text);
        });
    }
}