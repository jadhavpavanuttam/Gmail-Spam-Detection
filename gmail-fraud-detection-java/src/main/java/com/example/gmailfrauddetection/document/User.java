package com.example.gmailfrauddetection.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String gmailAddress;
    private String passwordHash;
    private String role = "USER";
    private boolean gmailOwnershipVerified;
    private boolean gmailConnected;
    private String encryptedRefreshToken;
    private String historyId;
    private Instant createdAt = Instant.now();

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

    public String getGmailAddress() {
        return gmailAddress;
    }

    public void setGmailAddress(String v) {
        gmailAddress = v;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String v) {
        passwordHash = v;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String v) {
        role = v;
    }

    public boolean isGmailOwnershipVerified() {
        return gmailOwnershipVerified;
    }

    public void setGmailOwnershipVerified(boolean v) {
        gmailOwnershipVerified = v;
    }

    public boolean isGmailConnected() {
        return gmailConnected;
    }

    public void setGmailConnected(boolean v) {
        gmailConnected = v;
    }

    public String getEncryptedRefreshToken() {
        return encryptedRefreshToken;
    }

    public void setEncryptedRefreshToken(String v) {
        encryptedRefreshToken = v;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String v) {
        historyId = v;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant v) {
        createdAt = v;
    }
}