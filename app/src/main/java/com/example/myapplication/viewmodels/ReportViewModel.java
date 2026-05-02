package com.example.myapplication.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myapplication.models.Enrollment;
import com.example.myapplication.models.Report;
import com.example.myapplication.utils.MockData;
import java.util.List;
import java.util.Locale;

public class ReportViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Report>> reports = new MutableLiveData<>();
    private final MutableLiveData<String> totalAnnualRevenue = new MutableLiveData<>();
    private final MutableLiveData<String> revenueTrend = new MutableLiveData<>();

    public ReportViewModel(@NonNull Application application) {
        super(application);
        fetchReports();
        fetchRevenueStats();
    }

    public void fetchReports() {
        reports.setValue(MockData.getReports());
    }

    public void fetchRevenueStats() {
        double total = 0;
        for (Enrollment e : MockData.getEnrollments()) {
            total += e.getPaidAmount();
        }
        totalAnnualRevenue.setValue(String.format(Locale.US, "$%,.2f", total));
        revenueTrend.setValue("Total Lifetime Revenue");
    }

    public LiveData<List<Report>> getReports() { return reports; }
    public LiveData<String> getTotalAnnualRevenue() { return totalAnnualRevenue; }
    public LiveData<String> getRevenueTrend() { return revenueTrend; }
}
