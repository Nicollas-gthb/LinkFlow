package com.linkflow.dto;

public record AuthResponse(
        String token,
        String type,
        String name,
        String email,
        String role
) {
    public static AuthResponse of(String token, String name, String email, String role) {
        return new AuthResponse(token, "Bearer", name, email, role);
    }
}
