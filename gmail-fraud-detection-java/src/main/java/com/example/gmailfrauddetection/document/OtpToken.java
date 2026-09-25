package com.example.gmailfrauddetection.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("otp_tokens")
public class OtpToken {
    @Id
    private String id;
    private String username;
    private String otpHash;
    private Instant expiresAt;
    private int attempts;
    private boolean used;
    private Instant lastSentAt;

    public String getId() {
        return id;
    }

    public void setId(String v) {
        id = v;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String v) {
        username = v;
    }

    public String getOtpHash() {
        return otpHash;
    }

    public void setOtpHash(String v) {
        otpHash = v;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant v) {
        expiresAt = v;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int v) {
        attempts = v;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean v) {
        used = v;
    }

    public Instant getLastSentAt() {
        return lastSentAt;
    }

    public void setLastSentAt(Instant v) {
        lastSentAt = v;
    }
}