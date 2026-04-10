package com.example.do_an_mon_hoc_quanlikhoahoc_23521023;

// Import dùng để chuyển màn hình
import android.content.Intent;

// Dùng để "inflate" (tạo view từ XML)
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// UI component
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> courseList;

    public CourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    // Tạo View cho mỗi item (gọi khi RecyclerView cần tạo item mới)
    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    // Gán dữ liệu vào từng item (mỗi vị trí)
    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {

        Course course = courseList.get(position);
        holder.tvTitle.setText(course.getTitle());
        holder.imgCourse.setImageResource(course.getImageResource());

        //  Bắt sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CourseActivity.class);
            intent.putExtra("course_name", course.getTitle());
            v.getContext().startActivity(intent);
        });
    }

    // Trả về số lượng item
    @Override
    public int getItemCount() {
        return courseList.size();
    }

    //  giữ reference của View (tối ưu hiệu năng)
    static class CourseViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCourse;
        TextView tvTitle;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCourse = itemView.findViewById(R.id.imgCourse);
            tvTitle = itemView.findViewById(R.id.tvCourseTitle);
        }
    }
}