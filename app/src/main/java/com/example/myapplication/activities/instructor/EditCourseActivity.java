package com.example.myapplication.activities.instructor;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.R;
import com.example.myapplication.models.Course;
import com.example.myapplication.viewmodels.EditCourseViewModel;

public class EditCourseActivity extends AppCompatActivity {

    private EditCourseViewModel viewModel;
    private EditText etTitle, etDescription, etPrice, etThumbnailUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        etThumbnailUrl = findViewById(R.id.etThumbnailUrl);

        viewModel = new ViewModelProvider(this).get(EditCourseViewModel.class);

        // Receive course object from Intent (if editing existing)
        Course existingCourse = (Course) getIntent().getSerializableExtra("COURSE");
        if (existingCourse != null) {
            viewModel.setCourse(existingCourse);
        } else {
            // Initialize empty course for new course mode
            Course newCourse = new Course();
            newCourse.setId(null);
            newCourse.setTitle("");
            newCourse.setDescription("");
            newCourse.setPrice(0.0);
            newCourse.setCategoryId(null); // Prevents UUID syntax error 'cat_1'
            viewModel.setCourse(newCourse);
        }

        viewModel.getCourse().observe(this, course -> {
            if (course != null) {
                if (etTitle.getText().toString().isEmpty()) etTitle.setText(course.getTitle());
                if (etDescription.getText().toString().isEmpty()) etDescription.setText(course.getDescription());
                if (etPrice.getText().toString().isEmpty()) etPrice.setText(String.valueOf(course.getPrice()));
                if (etThumbnailUrl.getText().toString().isEmpty()) etThumbnailUrl.setText(course.getThumbnailUrl());
            }
        });

        viewModel.getSaveSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Course created/updated successfully!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                findViewById(R.id.btnSave).setEnabled(true);
                ((android.widget.Button)findViewById(R.id.btnSave)).setText("Save Changes");
                Toast.makeText(this, "Save Failed: " + error, Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String desc = etDescription.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();
            String thumbnailUrl = etThumbnailUrl.getText().toString().trim();
            
            if (title.isEmpty() || desc.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double price = Double.parseDouble(priceStr);
                v.setEnabled(false);
                ((android.widget.Button)v).setText("Saving...");
                viewModel.saveCourse(title, desc, price, thumbnailUrl);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
