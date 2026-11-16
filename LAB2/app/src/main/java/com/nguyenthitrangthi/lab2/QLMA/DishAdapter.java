package com.nguyenthitrangthi.lab2.QLMA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenthitrangthi.lab2.R;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private final Context context;
    private final int layoutId;

    public DishAdapter(@NonNull Context context, int resource, @NonNull List<Dish> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        }

        Dish dish = getItem(position);

        if (dish != null) {
            ImageView ivDish = convertView.findViewById(R.id.iv_dish);
            TextView tvDishName = convertView.findViewById(R.id.tv_dish_name);
            ImageView ivPromo = convertView.findViewById(R.id.iv_promotion);

            ivDish.setImageResource(dish.getThumbnail().getImg());
            tvDishName.setText(dish.getName());

            // Kích hoạt hiệu ứng chữ chạy (Marquee) khi tên món ăn quá dài
            tvDishName.setSelected(true);

            if (dish.isPromotion()) {
                ivPromo.setVisibility(View.VISIBLE);
            } else {
                ivPromo.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}