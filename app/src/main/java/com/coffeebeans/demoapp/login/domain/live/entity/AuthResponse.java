package com.coffeebeans.demoapp.login.domain.live.entity;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("message")
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}