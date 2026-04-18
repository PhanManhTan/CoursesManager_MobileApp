package com.example.myapplication.data.remote;

import com.example.myapplication.models.Comment;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentApi {
    // Get all Comments
    @GET("comments?select=*")
    Call<List<Comment>> getAll();

    // Get Comment by ID
    @GET("comments?id=eq.{id}&select=*")
    Call<List<Comment>> getById(@Path("id") String id);

    // Insert new Comment
    @POST("comments")
    Call<Void> insert(@Body Comment comment);

    // Update Comment by ID
    @PATCH("comments?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Comment comment);

    // Delete Comment by ID
    @DELETE("comments?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
