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

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {
    private final Context context;

    public ThumbnailAdapter(@NonNull Context context, int resource, @NonNull Thumbnail[] objects) {
        super(context, resource, objects);
        this.context = context;
    }

    // Giao diện hiển thị trên thanh Spinner khi đã chọn xong
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_selected_thumbnail, parent, false);
        }
        Thumbnail item = getItem(position);
        if (item != null) {
            TextView tvSelected = convertView.findViewById(R.id.tv_selected_thumbnail);
            tvSelected.setText(item.getName());
        }
        return convertView;
    }

    // Giao diện hiển thị trong hộp thoại Dialog khi bấm vào Spinner
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false);
        }
        Thumbnail item = getItem(position);
        if (item != null) {
            TextView tvName = convertView.findViewById(R.id.tv_thumbnail_name);
            ImageView ivThumb = convertView.findViewById(R.id.iv_thumbnail);

            tvName.setText(item.getName());
            ivThumb.setImageResource(item.getImg());
        }

        return convertView;
    }
}