package com.example.myapplication.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myapplication.models.Course;
import com.example.myapplication.utils.MockData;
import com.example.myapplication.utils.SessionManager;
import java.util.List;
import java.util.Locale;

public class InstructorViewModel extends AndroidViewModel {
    private final String instructorId;

    private final MutableLiveData<String> totalStudents = new MutableLiveData<>("0");
    private final MutableLiveData<String> monthlyRevenue = new MutableLiveData<>("$0");
    private final MutableLiveData<String> avgRating = new MutableLiveData<>("0.0 ★");
    private final MutableLiveData<String> liveCourses = new MutableLiveData<>("0");

    public InstructorViewModel(@NonNull Application application) {
        super(application);
        SessionManager sessionManager = new SessionManager(application);
        this.instructorId = sessionManager.getUserId();
        
        refreshStats();
    }

    public void refreshStats() {
        // Use MockData directly
        List<Course> instructorCourses = MockData.getCoursesByInstructor(instructorId != null ? instructorId : "instr_id");
        
        int liveCount = 0;
        for (Course c : instructorCourses) {
            if ("approved".equalsIgnoreCase(c.getStatus()) || "published".equalsIgnoreCase(c.getStatus())) {
                liveCount++;
            }
        }
        liveCourses.setValue(String.valueOf(liveCount));

        // Mock values for other stats since MockData enrollments are empty
        totalStudents.setValue("156");
        monthlyRevenue.setValue("$1,240");
        avgRating.setValue("4.8 ★");
    }

    public LiveData<String> getTotalStudents() { return totalStudents; }
    public LiveData<String> getMonthlyRevenue() { return monthlyRevenue; }
    public LiveData<String> getAvgRating() { return avgRating; }
    public LiveData<String> getLiveCourses() { return liveCourses; }
}
