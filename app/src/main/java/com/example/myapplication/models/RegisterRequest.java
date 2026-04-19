package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("data")
    public Map<String, String> data;

    public RegisterRequest(String email, String password, String fullName, String phone) {
        this.email    = email;
        this.password = password;
        this.data     = new HashMap<>();
        this.data.put("full_name", fullName);
        this.data.put("phone", phone);
        this.data.put("role", "student");
    }
}
