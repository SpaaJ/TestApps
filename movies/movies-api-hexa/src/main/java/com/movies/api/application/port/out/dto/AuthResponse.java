package com.movies.api.application.port.out.dto;

public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private int expiresIn;

    public AuthResponse(String token, int expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() { return token; }
    public String getType() { return type; }
    public int getExpiresIn() { return expiresIn; }
}
