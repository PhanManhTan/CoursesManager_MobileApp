package com.example.myapplication.data.remote;

import com.example.myapplication.models.Review;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewApi {
    // Get all Reviews
    @GET("reviews?select=*")
    Call<List<Review>> getAll();

    // Get Review by ID
    @GET("reviews?id=eq.{id}&select=*")
    Call<List<Review>> getById(@Path("id") String id);

    // Insert new Review
    @POST("reviews")
    Call<Void> insert(@Body Review review);

    // Update Review by ID
    @PATCH("reviews?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Review review);

    // Delete Review by ID
    @DELETE("reviews?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
