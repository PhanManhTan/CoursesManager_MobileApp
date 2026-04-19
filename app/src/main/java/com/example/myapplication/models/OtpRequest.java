package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class OtpRequest {
    @SerializedName("email")
    public String email;

    @SerializedName("token")
    public String token;

    @SerializedName("type")
    public String type;

    public OtpRequest(String email, String token, String type) {
        this.email = email;
        this.token = token;
        this.type  = type;
    }

    public static OtpRequest forSignup(String email, String token) {
        return new OtpRequest(email, token, "signup");
    }

    public static OtpRequest forRecovery(String email, String token) {
        return new OtpRequest(email, token, "recovery");
    }
}
