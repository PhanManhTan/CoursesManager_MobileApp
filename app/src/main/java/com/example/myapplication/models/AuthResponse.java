package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("refresh_token")
    public String refreshToken;

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("expires_in")
    public int expiresIn;

    @SerializedName("user")
    public AuthUser user;

    public static class AuthUser {
        @SerializedName("id")
        public String id;

        @SerializedName("email")
        public String email;

        @SerializedName("user_metadata")
        public UserMetadata userMetadata;
    }

    public static class UserMetadata {
        @SerializedName("full_name")
        public String fullName;

        @SerializedName("phone")
        public String phone;

        @SerializedName("role")
        public String role;
    }
}
