package com.example.myapplication.activities.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.InstructorCourseAdapter;
import com.example.myapplication.models.Course;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity
        implements InstructorCourseAdapter.OnCourseActionListener {

    private RecyclerView rvInstructorCourses;
    private InstructorCourseAdapter courseAdapter;
    private List<Course> courseList;
    private FloatingActionButton fabAddCourse;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_course_list);

        // Khởi tạo View
        rvInstructorCourses = findViewById(R.id.rvInstructorCourses);
        fabAddCourse = findViewById(R.id.fabAddCourse);
        bottomNav = findViewById(R.id.bottomNav);
        TextView tvTitle = findViewById(R.id.tvTitle);
        
        if (tvTitle != null) tvTitle.setText("My Courses");

        setupRecyclerView();
        loadCourses();
        setupNavigation();
        
        if (fabAddCourse != null) {
            fabAddCourse.setOnClickListener(v -> {
                Intent intent = new Intent(this, EditCourseActivity.class);
                intent.putExtra("IS_NEW_COURSE", true);
                startActivity(intent);
            });
        }
    }

    private void setupRecyclerView() {
        courseList = new ArrayList<>();
        courseAdapter = new InstructorCourseAdapter(this, courseList, this);
        rvInstructorCourses.setLayoutManager(new LinearLayoutManager(this));
        rvInstructorCourses.setAdapter(courseAdapter);
    }

    private void loadCourses() {
        courseList.clear();
        // Mockup data for courses
        courseList.add(new Course("1", "Android Development Pro", 24, "12h 30m", 49.99, "PUBLISHED", R.drawable.image_courses));
        courseList.add(new Course("2", "UI/UX Design Fundamentals", 15, "08h 20m", 29.99, "DRAFT", R.drawable.image_courses));
        courseList.add(new Course("3", "NodeJS for Beginners", 30, "20h 15m", 39.99, "PUBLISHED", R.drawable.image_courses));
        courseAdapter.notifyDataSetChanged();
    }

    private void setupNavigation() {
        if (bottomNav != null) {
            // No specific item in menu for "My Courses", usually it's under Home or a separate tab.
            // Since there's no "Courses" item in the menu, we don't highlight anything or we can highlight Home.
            bottomNav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_instructor_home) {
                    startActivity(new Intent(this, InstructorDashboardActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.nav_instructor_students) {
                    startActivity(new Intent(this, StudentListActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.nav_instructor_revenue) {
                    startActivity(new Intent(this, RevenueActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.nav_instructor_account) {
                    startActivity(new Intent(this, com.example.myapplication.activities.common.AccountActivity.class));
                    finish();
                    return true;
                }
                return false;
            });
        }
    }

    @Override
    public void onCourseClick(Course course) {
        Intent intent = new Intent(this, EditCourseActivity.class);
        intent.putExtra("IS_NEW_COURSE", false);
        intent.putExtra("COURSE_ID", course.getId());
        intent.putExtra("COURSE_TITLE", course.getTitle());
        startActivity(intent);
    }

    @Override
    public void onCourseMenuClick(Course course, View anchor) {
        onCourseClick(course);
    }
}
