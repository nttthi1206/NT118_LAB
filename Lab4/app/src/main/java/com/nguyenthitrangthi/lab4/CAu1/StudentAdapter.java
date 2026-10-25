package com.nguyenthitrangthi.lab4.CAu1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nguyenthitrangthi.lab4.R;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Student student);
        void onItemLongClick(Student student);
    }

    public StudentAdapter(List<Student> studentList, OnItemClickListener listener) {
        this.studentList = studentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.tvName.setText(student.getName());
        holder.tvCode.setText("MSSV: " + student.getStudentId());
        holder.tvPhone.setText("SĐT: " + student.getPhone());

        // Bấm vào để sang trang chi tiết
        holder.itemView.setOnClickListener(v -> listener.onItemClick(student));

        // Nhấn giữ để xóa
        holder.itemView.setOnLongClickListener(v -> {
            listener.onItemLongClick(student);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCode, tvPhone;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvPhone = itemView.findViewById(R.id.tv_phone);
        }
    }
}