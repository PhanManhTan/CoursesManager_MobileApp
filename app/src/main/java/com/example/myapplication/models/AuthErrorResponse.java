package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class AuthErrorResponse {
    @SerializedName("msg")
    public String msg;

    @SerializedName("message")
    public String message;

    @SerializedName("error_code")
    public String errorCode;

    @SerializedName("error_description")
    public String errorDescription;

    public String getReadableMessage() {
        if (msg != null && !msg.isEmpty()) return msg;
        if (message != null && !message.isEmpty()) return message;
        if (errorDescription != null && !errorDescription.isEmpty()) return errorDescription;
        return "An unknown error occurred";
    }
}
