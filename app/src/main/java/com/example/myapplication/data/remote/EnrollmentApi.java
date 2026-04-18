package com.example.myapplication.data.remote;

import com.example.myapplication.models.Enrollment;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EnrollmentApi {
    // Get all Enrollments
    @GET("enrollments?select=*")
    Call<List<Enrollment>> getAll();

    // Get Enrollment by ID
    @GET("enrollments?id=eq.{id}&select=*")
    Call<List<Enrollment>> getById(@Path("id") String id);

    // Insert new Enrollment
    @POST("enrollments")
    Call<Void> insert(@Body Enrollment enrollment);

    // Update Enrollment by ID
    @PATCH("enrollments?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Enrollment enrollment);

    // Delete Enrollment by ID
    @DELETE("enrollments?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
