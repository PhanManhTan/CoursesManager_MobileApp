package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class ResendOtpRequest {
    @SerializedName("type")
    public String type;

    @SerializedName("email")
    public String email;

    public ResendOtpRequest(String email, String type) {
        this.email = email;
        this.type  = type;
    }
}
