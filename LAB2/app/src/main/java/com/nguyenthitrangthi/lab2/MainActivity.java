package com.nguyenthitrangthi.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenthitrangthi.lab2.TH4.TH4;
import com.nguyenthitrangthi.lab2.TH5.TH5;
import com.nguyenthitrangthi.lab2.TH6.TH6;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTh1 = findViewById(R.id.btn_th1);
        Button btnTh3 = findViewById(R.id.btn_th3);
        Button btnTh4 = findViewById(R.id.btn_th4);
        Button btnTh5 = findViewById(R.id.btn_th5);
        Button btnTh6 = findViewById(R.id.btn_th6);
        Button btnQlma = findViewById(R.id.btn_qlma);

        btnTh1.setOnClickListener(v -> startActivity(new Intent(this, TH1.class)));
        btnTh3.setOnClickListener(v -> startActivity(new Intent(this, TH3.class)));
        btnTh4.setOnClickListener(v -> startActivity(new Intent(this, TH4.class)));
        btnTh5.setOnClickListener(v -> startActivity(new Intent(this, TH5.class)));
        btnTh6.setOnClickListener(v -> startActivity(new Intent(this, TH6.class)));
        btnQlma.setOnClickListener(v -> startActivity(new Intent(this, com.nguyenthitrangthi.lab2.QLMA.QLMA.class)));
    }
}