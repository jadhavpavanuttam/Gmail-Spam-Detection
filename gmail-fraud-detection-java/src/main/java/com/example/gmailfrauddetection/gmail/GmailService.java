package com.example.gmailfrauddetection.gmail;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GmailService {
    public List<Map<String, Object>> messages() {
        return List.of(Map.of("messageId", "demo-message-1", "status", "Gmail API adapter ready", "note", "Configure Google OAuth credentials to retrieve live Gmail messages."));
    }

    public Map<String, Object> status() {
        return Map.of("connected", false, "provider", "Gmail", "scope", "gmail.readonly");
    }
}