package com.example.myapplication.data.remote;

import com.example.myapplication.models.Report;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportApi {
    // Get all Reports
    @GET("reports?select=*")
    Call<List<Report>> getAll();

    // Get Report by ID
    @GET("reports?id=eq.{id}&select=*")
    Call<List<Report>> getById(@Path("id") String id);

    // Insert new Report
    @POST("reports")
    Call<Void> insert(@Body Report report);

    // Update Report by ID
    @PATCH("reports?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Report report);

    // Delete Report by ID
    @DELETE("reports?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
