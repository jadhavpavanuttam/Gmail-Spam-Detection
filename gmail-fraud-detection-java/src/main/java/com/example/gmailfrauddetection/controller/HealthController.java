package com.example.gmailfrauddetection.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HealthController {
    @GetMapping("/api/public/demo")
    public Map<String, String> demo() {
        return Map.of("status", "UP", "message", "Java-only Gmail fraud detection application");
    }
}
