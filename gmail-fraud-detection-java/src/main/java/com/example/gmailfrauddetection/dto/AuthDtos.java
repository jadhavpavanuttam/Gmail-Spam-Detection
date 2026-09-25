package com.example.gmailfrauddetection.dto;

public final class AuthDtos {
    private AuthDtos() {
    }

    public record RegisterRequest(String username, String gmailAddress, String password) {
    }

    public record OtpRequest(String username, String otp) {
    }

    public record LoginRequest(String username, String password) {
    }

    public record AuthResponse(String accessToken, String refreshToken, String username) {
    }

    public record RefreshRequest(String refreshToken) {
    }
}