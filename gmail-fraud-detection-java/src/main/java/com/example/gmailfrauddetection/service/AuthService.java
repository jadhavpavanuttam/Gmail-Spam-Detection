package com.example.gmailfrauddetection.service;

import com.example.gmailfrauddetection.document.User;
import com.example.gmailfrauddetection.dto.AuthDtos.*;
import com.example.gmailfrauddetection.repository.UserRepository;
import com.example.gmailfrauddetection.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final OtpService otp;

    public AuthService(UserRepository u, PasswordEncoder e, JwtService j, OtpService o) {
        users = u;
        encoder = e;
        jwt = j;
        otp = o;
    }

    public String register(RegisterRequest r) {
        if (!StringUtils.hasText(r.username()) || !StringUtils.hasText(r.gmailAddress()) || !StringUtils.hasText(r.password()))
            throw new IllegalArgumentException("All registration fields are required");
        if (!r.gmailAddress().matches("^[A-Za-z0-9._%+-]+@gmail\\.com$"))
            throw new IllegalArgumentException("A valid Gmail address is required");
        if (r.password().length() < 8)
            throw new IllegalArgumentException("Password must contain at least 8 characters");
        if (users.existsByUsername(r.username())) throw new IllegalArgumentException("Username already exists");
        if (users.existsByGmailAddress(r.gmailAddress().toLowerCase()))
            throw new IllegalArgumentException("Gmail address already exists");
        User u = new User();
        u.setUsername(r.username());
        u.setGmailAddress(r.gmailAddress().toLowerCase());
        u.setPasswordHash(encoder.encode(r.password()));
        users.save(u);
        otp.send(u);
        return "Registration successful. Verify the OTP sent to the configured SMTP address.";
    }

    public AuthResponse login(LoginRequest r) {
        User u = users.findByUsername(r.username()).orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!encoder.matches(r.password(), u.getPasswordHash()))
            throw new IllegalArgumentException("Invalid credentials");
        if (!u.isGmailOwnershipVerified()) throw new IllegalArgumentException("Verify OTP before login");
        return new AuthResponse(jwt.createAccessToken(u.getUsername(), u.getRole()), jwt.createRefreshToken(u.getUsername()), u.getUsername());
    }
}