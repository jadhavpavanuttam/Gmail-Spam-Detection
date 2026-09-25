package com.example.gmailfrauddetection.controller;

import com.example.gmailfrauddetection.dto.AuthDtos.*;
import com.example.gmailfrauddetection.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;
    private final OtpService otp;

    public AuthController(AuthService a, OtpService o) {
        auth = a;
        otp = o;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest r) {
        return auth.register(r);
    }

    @PostMapping("/verify-otp")
    public String verify(@RequestBody OtpRequest r) {
        otp.verify(r.username(), r.otp());
        return "OTP verified. You can now log in.";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest r) {
        return auth.login(r);
    }

    @PostMapping("/refresh")
    public String refresh(@RequestBody RefreshRequest r) {
        return "Refresh-token endpoint placeholder: issue a new access token after validating the refresh token.";
    }
}
/*

{
    "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbnNhcmkiLCJyb2xlIjoiVVNFUiIsInR5cGUiOiJhY2Nlc3MiLCJpYXQiOjE3OTAzMDQ4NjcsImV4cCI6MTc5MDMwNTc2N30.YYCt2pHisrL9flETvne55JCxVg1m7u4ppipY9eWKESyRqZ6hIWiE1mIHz1K2e-gC",
    "refreshToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbnNhcmkiLCJyb2xlIjoiVVNFUiIsInR5cGUiOiJyZWZyZXNoIiwiaWF0IjoxNzkwMzA0ODY3LCJleHAiOjE3OTA5MDk2Njd9.RIlemKlswq0LuuBdU-PsKg-a1aSFY14VFjb8N8CU3k3Tsa68Jk9zt_JvC9mIaqdM",
    "username": "ansari"
}

}
 */