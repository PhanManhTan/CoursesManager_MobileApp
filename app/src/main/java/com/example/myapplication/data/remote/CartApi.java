package com.example.myapplication.data.remote;

import com.example.myapplication.models.Cart;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartApi {
    // Get all Carts
    @GET("carts?select=*")
    Call<List<Cart>> getAll();

    // Get Cart by ID
    @GET("carts?id=eq.{id}&select=*")
    Call<List<Cart>> getById(@Path("id") String id);

    // Insert new Cart
    @POST("carts")
    Call<Void> insert(@Body Cart cart);

    // Update Cart by ID
    @PATCH("carts?id=eq.{id}")
    Call<Void> update(@Path("id") String id, @Body Cart cart);

    // Delete Cart by ID
    @DELETE("carts?id=eq.{id}")
    Call<Void> delete(@Path("id") String id);
}
