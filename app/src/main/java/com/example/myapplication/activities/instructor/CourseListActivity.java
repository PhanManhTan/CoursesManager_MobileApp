package com.example.myapplication.activities.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.InstructorCourseAdapter;
import com.example.myapplication.models.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity
        implements InstructorCourseAdapter.OnCourseActionListener {

    private RecyclerView rvInstructorCourses;
    private InstructorCourseAdapter courseAdapter;
    private List<Course> courseList;
    private FloatingActionButton fabAddCourse;
    private ImageView btnMenu;
    private TextView tvTitle;

    private static final int REQUEST_CODE_COURSE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_course_list);

        initViews();
        setupRecyclerView();
        loadCourses();
        setupListeners();
    }

    private void initViews() {
        rvInstructorCourses = findViewById(R.id.rvInstructorCourses);
        fabAddCourse        = findViewById(R.id.fabAddCourse);
        btnMenu             = findViewById(R.id.btnMenu);
        tvTitle             = findViewById(R.id.tvTitle);
    }

    private void setupRecyclerView() {
        courseList    = new ArrayList<>();
        courseAdapter = new InstructorCourseAdapter(this, courseList, this);

        rvInstructorCourses.setLayoutManager(new LinearLayoutManager(this));
        rvInstructorCourses.setAdapter(courseAdapter);
        rvInstructorCourses.setHasFixedSize(true);
    }

    private void loadCourses() {
        // Dữ liệu giả lập
        courseList.clear();
        // Using ic_launcher_background or ic_home as placeholders since course_thumb_x are missing
        courseList.add(new Course("1", "Lập trình Android Pro", 24, "12h 30m", 49.99, "PUBLISHED", R.drawable.ic_home));
        courseList.add(new Course("2", "Thiết kế giao diện UI/UX", 15, "08h 20m", 29.99, "DRAFT", R.drawable.ic_home));
        courseList.add(new Course("3", "NodeJS Masterclass", 30, "20h 00m", 59.99, "PUBLISHED", R.drawable.ic_home));

        courseAdapter.notifyDataSetChanged();
    }

    private void setupListeners() {
        // 1. Ấn nút FAB (+) -> Mở màn hình TRỐNG để thêm mới
        fabAddCourse.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditCoursesActivity.class);
            intent.putExtra("IS_NEW_COURSE", true); // Đánh dấu là thêm mới
            startActivityForResult(intent, REQUEST_CODE_COURSE);
        });

        btnMenu.setOnClickListener(v ->
                Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
        );
    }

    // ── InstructorCourseAdapter.OnCourseActionListener ──────────────────────

    @Override
    public void onCourseClick(Course course) {
        // 2. Ấn vào một khóa học -> Mở màn hình CÓ DỮ LIỆU để sửa
        Intent intent = new Intent(this, EditCoursesActivity.class);

        intent.putExtra("IS_NEW_COURSE", false); // Đánh dấu là sửa khóa học cũ
        intent.putExtra("COURSE_ID", course.getId());
        intent.putExtra("COURSE_TITLE", course.getTitle());
        intent.putExtra("COURSE_PRICE", course.getPrice());

        startActivityForResult(intent, REQUEST_CODE_COURSE);
    }

    @Override
    public void onCourseMenuClick(Course course, View anchor) {
        // Show popup menu nếu cần
        Toast.makeText(this, "Options cho: " + course.getTitle(), Toast.LENGTH_SHORT).show();
    }

    // ── onActivityResult ─────────────────────────────────────────────────────

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Sau khi lưu ở màn hình kia và finish(), quay lại đây sẽ refresh list
        if (requestCode == REQUEST_CODE_COURSE && resultCode == RESULT_OK) {
            loadCourses();
        }
    }
}
