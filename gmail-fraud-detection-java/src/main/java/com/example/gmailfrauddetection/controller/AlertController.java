package com.example.gmailfrauddetection.controller;

import com.example.gmailfrauddetection.document.Alert;
import com.example.gmailfrauddetection.repository.AlertRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
    private final AlertRepository repo;

    public AlertController(AlertRepository r) {
        repo = r;
    }

    @GetMapping
    public List<Alert> list(Authentication a) {
        return repo.findByUserIdOrderByCreatedAtDesc(a.getName());
    }

    @GetMapping("/{id}")
    public Alert one(@PathVariable String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("ALERT WITH " + id + " IS NOT FOUND"));
    }

    @PatchMapping("/{id}/status")
    public Alert status(@PathVariable String id, @RequestBody Map<String, String> b) {
        var x = repo.findById(id).orElseThrow();
        x.setStatus(b.getOrDefault("status", "OPEN"));
        return repo.save(x);
    }
}