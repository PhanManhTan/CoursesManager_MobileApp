package com.example.myapplication.activities.student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activities.common.CartActivity;
import com.example.myapplication.adapters.ChapterAdapter;
import com.example.myapplication.models.Course;
import com.example.myapplication.utils.MockData;

public class CourseDetailActivity extends AppCompatActivity {

    private RecyclerView rvLessons;
    private ImageView btnBack, ivThumbnail;
    private Button btnEnroll;
    private TextView tvCourseTitle, tvDiscountPrice, tvOriginalPrice, tvPerDiscount, tvDesDetail;

    private Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        initViews();
        loadCourseData();
        setupRecyclerView();

        btnBack.setOnClickListener(v -> finish());

        btnEnroll.setOnClickListener(v -> {
            if (currentCourse != null) {
                MockData.addToCart(currentCourse);
                Toast.makeText(this, "Added to cart!", Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(this, CartActivity.class));
        });
    }

    private void initViews() {
        rvLessons = findViewById(R.id.rvLessons);
        btnBack = findViewById(R.id.btnBack);
        btnEnroll = findViewById(R.id.btnEnroll);
        ivThumbnail = findViewById(R.id.ivThumbnail);
        tvCourseTitle = findViewById(R.id.tvCourseTitle);
        tvDiscountPrice = findViewById(R.id.tvDiscountPrice);
        tvOriginalPrice = findViewById(R.id.tvOriginalPrice);
        tvPerDiscount = findViewById(R.id.tvPerDiscount);
        tvDesDetail = findViewById(R.id.tvDesDetail);
    }

    private void loadCourseData() {
        String courseId = getIntent().getStringExtra("course_id");
        currentCourse = courseId != null ? MockData.getCourseById(courseId) : null;

        if (currentCourse == null) return;

        if (tvCourseTitle != null) tvCourseTitle.setText(currentCourse.getTitle());
        if (tvDesDetail != null && currentCourse.getDescription() != null)
            tvDesDetail.setText(currentCourse.getDescription());
        if (tvDiscountPrice != null)
            tvDiscountPrice.setText(String.format("$%.2f", currentCourse.getDiscountPrice()));
        if (tvOriginalPrice != null)
            tvOriginalPrice.setText(String.format("$%.2f", currentCourse.getPrice()));
        if (tvPerDiscount != null && currentCourse.getPrice() > 0) {
            int discount = (int) ((1 - currentCourse.getDiscountPrice() / currentCourse.getPrice()) * 100);
            tvPerDiscount.setText(discount + "% OFF");
        }
        if (ivThumbnail != null) {
            if (currentCourse.getThumbnailUrl() != null && !currentCourse.getThumbnailUrl().isEmpty()) {
                Glide.with(this).load(currentCourse.getThumbnailUrl())
                        .placeholder(R.drawable.image_courses)
                        .error(R.drawable.image_courses)
                        .into(ivThumbnail);
            } else {
                ivThumbnail.setImageResource(R.drawable.image_courses);
            }
        }
    }

    private void setupRecyclerView() {
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        rvLessons.setNestedScrollingEnabled(false);
        rvLessons.setAdapter(new ChapterAdapter());
    }
}
