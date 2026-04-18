package com.example.myapplication.data.remote;

import com.example.myapplication.models.Category;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryApi {
    // Get all Categories
    @GET("categories?select=*")
    Call<List<Category>> getAll();

    // Get Category by ID
    @GET("categories?id=eq.{id}&select=*")
    Call<List<Category>> getById(@Path("id") String id);

    // Insert new Category
    @POST("categories")
    Call<Void> insert(@Body Category category);

    // Update Category by ID
    @PATCH("categories?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Category category);

    // Delete Category by ID
    @DELETE("categories?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
