package com.nguyenthitrangthi.lab2.QLMA;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenthitrangthi.lab2.R;

import java.util.ArrayList;

public class QLMA extends AppCompatActivity {

    private EditText etName;
    private Spinner spnThumbnail;
    private CheckBox cbPromotion;
    private GridView gvDish;

    private ArrayList<Dish> dishList;
    private DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlma);

        // Ánh xạ View từ activity_qlma.xml
        etName = findViewById(R.id.et_dish_name);
        spnThumbnail = findViewById(R.id.spn_thumbnail);
        cbPromotion = findViewById(R.id.cb_promotion);
        Button btnAdd = findViewById(R.id.btn_add_dish);
        gvDish = findViewById(R.id.gv_dish);

        // 1. Thiết lập Spinner chọn ảnh Thumbnail
        ThumbnailAdapter thumbnailAdapter = new ThumbnailAdapter(
                this,
                R.layout.item_selected_thumbnail,
                Thumbnail.values()
        );
        spnThumbnail.setAdapter(thumbnailAdapter);

        // 2. Thiết lập GridView hiển thị danh sách món ăn (2 cột)
        dishList = new ArrayList<>();
        dishAdapter = new DishAdapter(this, R.layout.item_dish, dishList);
        gvDish.setAdapter(dishAdapter);

        // 3. Xử lý sự kiện nhấn nút "ADD A NEW DISH"
        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            Thumbnail selectedThumb = (Thumbnail) spnThumbnail.getSelectedItem();
            boolean isPromo = cbPromotion.isChecked();

            if (!name.isEmpty()) {
                Dish newDish = new Dish(name, selectedThumb, isPromo);
                dishList.add(newDish);
                dishAdapter.notifyDataSetChanged();

                // Hiển thị thông báo "Added successfully"
                Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show();

                // Reset form nhập
                etName.setText("");
                spnThumbnail.setSelection(0);
                cbPromotion.setChecked(false);
                etName.requestFocus();
            } else {
                Toast.makeText(this, "Vui lòng nhập tên món ăn!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}