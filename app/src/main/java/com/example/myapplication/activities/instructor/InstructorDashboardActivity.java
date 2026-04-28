package com.example.myapplication.activities.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.activities.admin.AdminDashboardActivity;
import com.example.myapplication.viewmodels.InstructorViewModel;

public class InstructorDashboardActivity extends AppCompatActivity {

    private InstructorViewModel viewModel;
    private TextView tvTotalStudents, tvMonthlyRevenue, tvAvgRating, tvLiveCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_dashboard);

        initViews();
        setupViewModel();
        setupListeners();
    }

    private void initViews() {
        tvTotalStudents = findViewById(R.id.tvTotalStudents);
        tvMonthlyRevenue = findViewById(R.id.tvMonthlyRevenue);
        tvAvgRating = findViewById(R.id.tvAvgRating);
        tvLiveCourses = findViewById(R.id.tvLiveCourses);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        // Observe data
        viewModel.getTotalStudents().observe(this, students -> tvTotalStudents.setText(students));
        viewModel.getMonthlyRevenue().observe(this, revenue -> tvMonthlyRevenue.setText(revenue));
        viewModel.getAvgRating().observe(this, rating -> tvAvgRating.setText(rating));
    }

    private void setupListeners() {
        // Mở danh sách khóa học
        findViewById(R.id.btnViewCourseList).setOnClickListener(v -> {
            startActivity(new Intent(this, CourseListActivity.class));
        });

        // Tạo khóa học mới
        findViewById(R.id.btnCreateCourse).setOnClickListener(v -> {
            Intent intent = new Intent(this, EditCoursesActivity.class);
            intent.putExtra("IS_NEW_COURSE", true);
            startActivity(intent);
        });

        findViewById(R.id.btnViewRevenue).setOnClickListener(v -> {
            startActivity(new Intent(this, RevenueActivity.class));
        });

        findViewById(R.id.btnManageStudents).setOnClickListener(v -> {
            startActivity(new Intent(this, StudentListActivity.class));
        });

        // Truy cập Admin Dashboard (Nhấn giữ tiêu đề)
        findViewById(R.id.header).setOnLongClickListener(v -> {
            startActivity(new Intent(this, AdminDashboardActivity.class));
            return true;
        });
    }
}
