package com.example.myapplication.activities.instructor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.R;
import com.example.myapplication.viewmodels.RevenueViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RevenueActivity extends AppCompatActivity {

    private RevenueViewModel viewModel;
    private BarChart barChart;
    private PieChart pieChart;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);
        bottomNav = findViewById(R.id.bottomNav);

        viewModel = new ViewModelProvider(this).get(RevenueViewModel.class);

        setupCharts();
        observeData();
        setupNavigation();

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupCharts() {
        // BarChart styling
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setTextColor(Color.WHITE);

        // PieChart styling
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.getLegend().setTextColor(Color.WHITE);
    }

    private void observeData() {
        viewModel.getBarEntries().observe(this, entries -> {
            BarDataSet dataSet = new BarDataSet(entries, "Monthly Revenue");
            dataSet.setColor(Color.parseColor("#5A4FCF"));
            dataSet.setValueTextColor(Color.WHITE);
            
            BarData data = new BarData(dataSet);
            barChart.setData(data);
            barChart.invalidate();
        });

        viewModel.getPieEntries().observe(this, entries -> {
            PieDataSet dataSet = new PieDataSet(entries, "Course Sales");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setValueTextColor(Color.WHITE);
            dataSet.setValueTextSize(12f);
            
            PieData data = new PieData(dataSet);
            pieChart.setData(data);
            pieChart.invalidate();
        });
    }

    private void setupNavigation() {
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_instructor_revenue);
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
