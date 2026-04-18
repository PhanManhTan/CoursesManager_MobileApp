package com.example.myapplication.data.remote;

import com.example.myapplication.models.Course;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CourseApi {
    // Get all Courses
    @GET("courses?select=*")
    Call<List<Course>> getAll();

    // Get Course by ID
    @GET("courses?id=eq.{id}&select=*")
    Call<List<Course>> getById(@Path("id") String id);

    // Insert new Course
    @POST("courses")
    Call<Void> insert(@Body Course course);

    // Update Course by ID
    @PATCH("courses?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Course course);

    // Delete Course by ID
    @DELETE("courses?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
