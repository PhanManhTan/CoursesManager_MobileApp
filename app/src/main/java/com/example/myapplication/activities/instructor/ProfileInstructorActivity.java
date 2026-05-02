package com.example.myapplication.activities.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.repository.CourseRepository;
import com.example.myapplication.data.repository.EnrollmentRepository;
import com.example.myapplication.data.repository.UserRepository;
import com.example.myapplication.models.Course;
import com.example.myapplication.models.Enrollment;
import com.example.myapplication.models.User;
import com.example.myapplication.utils.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class ProfileInstructorActivity extends AppCompatActivity {

    private LinearLayout lnInstructorCourses;
    private TextView tvName, tvBio, tvExpertise, tvStudentsCount;
    private BottomNavigationView bottomNav;
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private EnrollmentRepository enrollmentRepository;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_instructor);

        tvName = findViewById(R.id.tvName);
        tvBio = findViewById(R.id.tvBio);
        tvExpertise = findViewById(R.id.tvExpertise);
        tvStudentsCount = findViewById(R.id.tvStudentsCount);
        lnInstructorCourses = findViewById(R.id.lnInstructorCourses);
        ImageView btnBack = findViewById(R.id.btnBack);
        bottomNav = findViewById(R.id.bottomNav);

        userRepository = new UserRepository(this);
        courseRepository = new CourseRepository(this);
        enrollmentRepository = new EnrollmentRepository(this);
        sessionManager = new SessionManager(this);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        setupNavigation();
        loadMockupProfile(); // Load mockup first
        loadInstructorProfile(); // Then try live data
    }

    private void loadMockupProfile() {
        tvName.setText("John Instructor");
        tvBio.setText("Expert in Android Development with 10 years of experience. I love teaching and building robust mobile applications.");
        tvExpertise.setText("Senior Android Developer");
        tvStudentsCount.setText("1,240");
        
        List<Course> mockupCourses = new ArrayList<>();
        // Sửa lỗi: Truyền ID drawable (int) thay vì chuỗi rỗng ""
        mockupCourses.add(new Course("1", "Advanced Android Development", 20, "15h", 99.99, "published", R.drawable.image_courses));
        mockupCourses.add(new Course("2", "Kotlin Fundamentals", 12, "8h", 49.99, "published", R.drawable.image_courses));
        displayCourses(mockupCourses);
    }

    private void loadInstructorProfile() {
        String instructorId = sessionManager.getUserId();
        if (instructorId == null) return;

        userRepository.getById(instructorId, new UserRepository.RepositoryCallback<User>() {
            @Override
            public void onSuccess(User user) {
                if (user != null) {
                    tvName.setText(user.getFullName());
                    if (user.getBio() != null && !user.getBio().isEmpty()) {
                        tvBio.setText(user.getBio());
                    }
                    tvExpertise.setText(user.getRole() != null ? user.getRole() : "Instructor");
                }
            }
            @Override public void onError(String message) {}
        });

        courseRepository.getByInstructor(instructorId, new CourseRepository.RepositoryCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> courses) {
                if (courses != null && !courses.isEmpty()) {
                    displayCourses(courses);
                    calculateTotalStudents(courses);
                }
            }
            @Override public void onError(String message) {}
        });
    }

    private void calculateTotalStudents(List<Course> courses) {
        enrollmentRepository.getAll(new EnrollmentRepository.RepositoryCallback<List<Enrollment>>() {
            @Override
            public void onSuccess(List<Enrollment> allEnrollments) {
                int total = 0;
                if (allEnrollments != null && courses != null) {
                    for (Course course : courses) {
                        for (Enrollment e : allEnrollments) {
                            if (e.getCourseId() != null && e.getCourseId().equals(course.getId())) {
                                total++;
                            }
                        }
                    }
                }
                if (total > 0) tvStudentsCount.setText(String.valueOf(total));
            }
            @Override public void onError(String message) {}
        });
    }

    private void displayCourses(List<Course> courses) {
        lnInstructorCourses.removeAllViews();
        for (Course course : courses) {
            View itemView = getLayoutInflater().inflate(R.layout.item_course_search, null);

            TextView title = itemView.findViewById(R.id.tvCourseTitle);
            TextView instructor = itemView.findViewById(R.id.tvInstructor);
            TextView price = itemView.findViewById(R.id.tvPrice);
            ImageView thumb = itemView.findViewById(R.id.ivCourseThumb);

            if (title != null) title.setText(course.getTitle());
            if (instructor != null) instructor.setText(tvName.getText());
            if (price != null) price.setText("$" + course.getPrice());

            if (thumb != null) {
                if (course.getThumbnailUrl() != null && !course.getThumbnailUrl().isEmpty()) {
                    Glide.with(this)
                            .load(course.getThumbnailUrl())
                            .placeholder(R.drawable.image_courses)
                            .error(R.drawable.image_courses)
                            .into(thumb);
                } else if (course.getThumbnailResId() != 0) {
                    thumb.setImageResource(course.getThumbnailResId());
                } else {
                    thumb.setImageResource(R.drawable.image_courses);
                }
            }

            lnInstructorCourses.addView(itemView);
        }
    }

    private void setupNavigation() {
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_instructor_account);
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
}
