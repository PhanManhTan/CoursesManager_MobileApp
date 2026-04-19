package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class SendOtpRequest {
    @SerializedName("email")
    public String email;

    public SendOtpRequest(String email) {
        this.email = email;
    }
}
