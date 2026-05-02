package com.example.myapplication.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myapplication.models.Course;
import com.example.myapplication.utils.MockData;
import java.util.ArrayList;
import java.util.List;

public class CourseApprovalViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Course>> pendingCourses = new MutableLiveData<>();

    public CourseApprovalViewModel(@NonNull Application application) {
        super(application);
        fetchPendingCourses();
    }

    public void fetchPendingCourses() {
        List<Course> pending = new ArrayList<>();
        for (Course c : MockData.getCourses()) {
            if ("pending".equalsIgnoreCase(c.getStatus())) pending.add(c);
        }
        pendingCourses.setValue(pending);
    }

    public void approveCourse(Course course) {
        course.setStatus("approved");
        fetchPendingCourses();
    }

    public void rejectCourse(Course course) {
        course.setStatus("rejected");
        fetchPendingCourses();
    }

    public LiveData<List<Course>> getPendingCourses() {
        return pendingCourses;
    }
}
