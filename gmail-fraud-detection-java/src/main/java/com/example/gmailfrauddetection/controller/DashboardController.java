package com.example.gmailfrauddetection.controller;

import com.example.gmailfrauddetection.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final EmailAnalysisRepository emails;
    private final AlertRepository alerts;

    public DashboardController(EmailAnalysisRepository e, AlertRepository a) {
        emails = e;
        alerts = a;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary(Authentication a) {
        String u = a.getName();
        return Map.of("totalAnalyzed", emails.countByUserId(u), "low", emails.countByUserIdAndRiskLevel(u, "LOW"), "medium", emails.countByUserIdAndRiskLevel(u, "MEDIUM"), "high", emails.countByUserIdAndRiskLevel(u, "HIGH"), "critical", emails.countByUserIdAndRiskLevel(u, "CRITICAL"), "openAlerts", alerts.countByUserIdAndStatus(u, "OPEN"), "recent", emails.findTop20ByUserIdOrderByReceivedAtDesc(u));
    }
}