package com.example.myapplication.data.remote;

import com.example.myapplication.models.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    // Get all Users
    @GET("users?select=*")
    Call<List<User>> getAll();

    // Get User by ID
    @GET("users?id=eq.{id}&select=*")
    Call<List<User>> getById(@Path("id") String id);

    // Insert new User
    @POST("users")
    Call<Void> insert(@Body User user);

    // Update User by ID
    @PATCH("users?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body User user);

    // Delete User by ID
    @DELETE("users?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
