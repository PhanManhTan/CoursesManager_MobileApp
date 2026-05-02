package com.example.myapplication.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myapplication.models.Course;
import com.example.myapplication.utils.MockData;
import java.util.List;

public class AdminViewModel extends AndroidViewModel {
    private final MutableLiveData<String> totalUsers = new MutableLiveData<>("0");
    private final MutableLiveData<String> activeCourses = new MutableLiveData<>("0");
    private final MutableLiveData<String> totalRevenue = new MutableLiveData<>("$0");
    private final MutableLiveData<String> pendingCourses = new MutableLiveData<>("0");

    public AdminViewModel(@NonNull Application application) {
        super(application);
        refreshStats();
    }

    public void refreshStats() {
        totalUsers.setValue(String.valueOf(MockData.getUsers().size()));
        
        List<Course> allCourses = MockData.getCourses();
        int activeCount = 0;
        int pendingCount = 0;
        for (Course c : allCourses) {
            if ("published".equalsIgnoreCase(c.getStatus())) activeCount++;
            else if ("pending".equalsIgnoreCase(c.getStatus())) pendingCount++;
        }
        
        activeCourses.setValue(String.valueOf(activeCount));
        pendingCourses.setValue(String.valueOf(pendingCount));
        totalRevenue.setValue("$15,240"); // Mock total revenue
    }

    public LiveData<String> getTotalUsers() { return totalUsers; }
    public LiveData<String> getActiveCourses() { return activeCourses; }
    public LiveData<String> getTotalRevenue() { return totalRevenue; }
    public LiveData<String> getPendingCourses() { return pendingCourses; }
}
