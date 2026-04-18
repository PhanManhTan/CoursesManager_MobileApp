package com.example.myapplication.data.remote;

import com.example.myapplication.models.Quiz;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuizApi {
    // Get all Quizzes
    @GET("quizzes?select=*")
    Call<List<Quiz>> getAll();

    // Get Quiz by ID
    @GET("quizzes?id=eq.{id}&select=*")
    Call<List<Quiz>> getById(@Path("id") String id);

    // Insert new Quiz
    @POST("quizzes")
    Call<Void> insert(@Body Quiz quiz);

    // Update Quiz by ID
    @PATCH("quizzes?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Quiz quiz);

    // Delete Quiz by ID
    @DELETE("quizzes?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
