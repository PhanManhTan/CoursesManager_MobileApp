package com.example.myapplication.data.remote;

import com.example.myapplication.models.Notification;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotificationApi {
    // Get all Notifications
    @GET("notifications?select=*")
    Call<List<Notification>> getAll();

    // Get Notification by ID
    @GET("notifications?id=eq.{id}&select=*")
    Call<List<Notification>> getById(@Path("id") String id);

    // Insert new Notification
    @POST("notifications")
    Call<Void> insert(@Body Notification notification);

    // Update Notification by ID
    @PATCH("notifications?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Notification notification);

    // Delete Notification by ID
    @DELETE("notifications?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
