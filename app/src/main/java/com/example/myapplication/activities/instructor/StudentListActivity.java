package com.example.myapplication.activities.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.StudentAdapter;
import com.example.myapplication.viewmodels.StudentListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentListActivity extends AppCompatActivity {

    private StudentListViewModel viewModel;
    private StudentAdapter adapter;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        RecyclerView rvStudents = findViewById(R.id.rvStudents);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new StudentAdapter();
        rvStudents.setAdapter(adapter);

        bottomNav = findViewById(R.id.bottomNav);

        viewModel = new ViewModelProvider(this).get(StudentListViewModel.class);
        viewModel.getStudents().observe(this, students -> adapter.setStudents(students));

        // Search Logic
        EditText etSearch = findViewById(R.id.etSearch);
        if (etSearch != null) {
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    viewModel.searchStudents(s.toString());
                }
                @Override public void afterTextChanged(Editable s) {}
            });
        }

        setupNavigation();

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupNavigation() {
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_instructor_students);
            bottomNav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_instructor_home) {
                    startActivity(new Intent(this, InstructorDashboardActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.nav_instructor_students) {
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
