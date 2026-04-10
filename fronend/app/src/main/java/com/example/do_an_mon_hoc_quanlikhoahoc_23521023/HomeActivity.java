package com.example.do_an_mon_hoc_quanlikhoahoc_23521023;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvCourses;
    private TextView tabSuggested, tabMyCourses;
    private Button btnSeeMore;
    private MaterialCardView btnMenuCard; // Khai báo nút Menu từ giao diện chính
    private boolean isSuggestedSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // 1. Ánh xạ các View
        rvCourses = findViewById(R.id.rvCourses);
        tabSuggested = findViewById(R.id.tabSuggested);
        tabMyCourses = findViewById(R.id.tabMyCourses);
        btnSeeMore = findViewById(R.id.btnSeeMore);
        btnMenuCard = findViewById(R.id.btnMenuCard);

        // Thiết lập Grid 2 cột cho danh sách khóa học
        rvCourses.setLayoutManager(new GridLayoutManager(this, 2));

        // Load dữ liệu mặc định
        loadData();

        // 2. Xử lý chuyển đổi Tab
        tabSuggested.setOnClickListener(v -> {
            if (!isSuggestedSelected) {
                isSuggestedSelected = true;
                updateTabStyle();
                loadData();
            }
        });

        tabMyCourses.setOnClickListener(v -> {
            if (isSuggestedSelected) {
                isSuggestedSelected = false;
                updateTabStyle();
                loadData();
            }
        });

        // 3. QUAN TRỌNG: Sự kiện click mở Sidebar Menu
        btnMenuCard.setOnClickListener(v -> showSidebarMenu());

        rvCourses.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    // Đã kéo tới cuối
                    btnSeeMore.setVisibility(View.VISIBLE);
                } else {
                    btnSeeMore.setVisibility(View.GONE);
                }
            }
        });
//buton xem thêm
        btnSeeMore.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CourseListActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Hàm khởi tạo và hiển thị Overlay Sidebar (Lớp phủ Menu)
     */
    private void showSidebarMenu() {
        // Tạo một Dialog mới
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Gán layout menu sidebar mà chúng ta đã xây dựng
        dialog.setContentView(R.layout.layout_sidebar);

        // Cấu hình cửa sổ (Window) để Dialog trượt từ bên phải và có nền trong suốt
        Window window = dialog.getWindow();
        if (window != null) {
            // Chiều rộng 280dp (theo layout), chiều cao chiếm toàn bộ màn hình
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

            // Xóa nền mặc định của Dialog để lộ phần bo góc (radius) của CardView
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // Đẩy Menu sang phía bên phải màn hình (đúng chất Sidebar)
            window.setGravity(Gravity.END);
        }

        // 4. Ánh xạ các thành phần BÊN TRONG Sidebar (phải dùng dialog.findViewById)
        MaterialCardView btnCloseMenu = dialog.findViewById(R.id.btnCloseMenu);
        LinearLayout menuProfile = dialog.findViewById(R.id.menuProfile);
        LinearLayout menuCourses = dialog.findViewById(R.id.menuCourses);
        LinearLayout menuMyCourses = dialog.findViewById(R.id.menuLearning);

        // Đóng Menu khi nhấn nút X hoặc Icon Menu bên trong
        btnCloseMenu.setOnClickListener(v -> dialog.dismiss());

        // Xử lý sự kiện khi nhấn vào các mục menu
        menuProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Đang mở Hồ sơ cá nhân", Toast.LENGTH_SHORT).show();
            dialog.dismiss(); // Đóng menu sau khi click
        });

        menuCourses.setOnClickListener(v -> {
            Toast.makeText(this, "Đang chuyển sang danh sách Khóa học", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        // Hồ sơ
        menuProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class)); // thêm
            dialog.dismiss();
        });

        // Khóa học
        menuCourses.setOnClickListener(v -> {
            startActivity(new Intent(this, CourseListActivity.class)); // sửa chỗ này
            dialog.dismiss();
        });

        // Học tập
        menuMyCourses.setOnClickListener(v -> {
            startActivity(new Intent(this,mycourseactivity.class)); // thêm
            dialog.dismiss();
        });
        // Cuối cùng là hiển thị Dialog lên màn hình
        dialog.show();
    }

    private void loadData() {
        List<Course> list = new ArrayList<>();
        String imgPrefix = isSuggestedSelected ? "rec" : "myc";
        String titlePrefix = isSuggestedSelected ? "Gợi ý " : "Của tôi ";

        for (int i = 1; i <= 6; i++) {
            String fileName = imgPrefix + i;
            int resId = getResources().getIdentifier(fileName, "drawable", getPackageName());
            if (resId == 0) resId = R.drawable.ic_launcher_background;
            list.add(new Course(titlePrefix + i, resId));
        }

        CourseAdapter adapter = new CourseAdapter(list);
        rvCourses.setAdapter(adapter);
    }

    private void updateTabStyle() {
        int activeColor = Color.parseColor("#112D4E");
        int inactiveColor = Color.GRAY;

        if (isSuggestedSelected) {
            tabSuggested.setTextColor(activeColor);
            tabSuggested.setTypeface(null, Typeface.BOLD);
            tabMyCourses.setTextColor(inactiveColor);
            tabMyCourses.setTypeface(null, Typeface.NORMAL);
        } else {
            tabMyCourses.setTextColor(activeColor);
            tabMyCourses.setTypeface(null, Typeface.BOLD);
            tabSuggested.setTextColor(inactiveColor);
            tabSuggested.setTypeface(null, Typeface.NORMAL);
        }
    }
}