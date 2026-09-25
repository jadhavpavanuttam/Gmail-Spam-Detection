package com.example.gmailfrauddetection.controller;

import com.example.gmailfrauddetection.repository.AuditLogRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditController {
    private final AuditLogRepository repo;

    public AuditController(AuditLogRepository r) {
        repo = r;
    }

    @GetMapping
    public Object list() {
        return repo.findTop50ByOrderByTimestampDesc();
    }
}
