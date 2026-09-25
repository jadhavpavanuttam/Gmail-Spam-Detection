package com.example.gmailfrauddetection.controller;

import com.example.gmailfrauddetection.gmail.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/gmail")
public class GmailController {
    private final GmailService gmail;

    public GmailController(GmailService g) {
        gmail = g;
    }

    @GetMapping("/status")
    public Map<String, Object> status() {
        return gmail.status();
    }

    @GetMapping("/messages")
    public List<Map<String, Object>> messages() {
        return gmail.messages();
    }

    @GetMapping("/messages/{id}")
    public Map<String, Object> message(@PathVariable String id) {
        return Map.of("messageId", id, "status", "Adapter endpoint ready");
    }

    @PostMapping("/sync")
    public Map<String, Object> sync() {
        return Map.of("status", "SYNC_REQUEST_ACCEPTED", "message", "Live sync requires configured Gmail OAuth credentials.");
    }
}