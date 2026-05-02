package com.example.myapplication.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myapplication.models.User;
import com.example.myapplication.utils.MockData;
import java.util.ArrayList;
import java.util.List;

public class StudentListViewModel extends AndroidViewModel {
    private final MutableLiveData<List<User>> students = new MutableLiveData<>();
    private List<User> allMyStudents = new ArrayList<>();

    public StudentListViewModel(@NonNull Application application) {
        super(application);
        fetchStudents();
    }

    public void fetchStudents() {
        List<User> mockStudents = new ArrayList<>();
        for (User u : MockData.getUsers()) {
            if ("Student".equalsIgnoreCase(u.getRole())) {
                mockStudents.add(u);
            }
        }
        
        // Neu list rong, lay dai vai user cho co du lieu
        if (mockStudents.isEmpty()) {
            mockStudents = MockData.getUsers();
        }

        allMyStudents = mockStudents;
        students.setValue(mockStudents);
    }

    public void searchStudents(String query) {
        if (query == null || query.isEmpty()) {
            students.setValue(new ArrayList<>(allMyStudents));
        } else {
            List<User> filtered = new ArrayList<>();
            String lowerQuery = query.toLowerCase();
            for (User user : allMyStudents) {
                if ((user.getFullName() != null && user.getFullName().toLowerCase().contains(lowerQuery)) ||
                    (user.getEmail() != null && user.getEmail().toLowerCase().contains(lowerQuery))) {
                    filtered.add(user);
                }
            }
            students.setValue(filtered);
        }
    }

    public LiveData<List<User>> getStudents() { return students; }
}
