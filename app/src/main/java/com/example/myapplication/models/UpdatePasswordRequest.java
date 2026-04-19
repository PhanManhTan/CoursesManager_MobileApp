package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class UpdatePasswordRequest {
    @SerializedName("password")
    public String password;

    public UpdatePasswordRequest(String password) {
        this.password = password;
    }
}
