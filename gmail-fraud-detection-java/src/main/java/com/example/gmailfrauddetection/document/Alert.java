package com.example.gmailfrauddetection.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.*;

@Document("alerts")
public class Alert {
    @Id
    private String id;
    @Indexed
    private String userId;
    private String emailAnalysisId;
    private String riskLevel;
    private String title;
    private List<String> reasons = new ArrayList<>();
    private String status = "OPEN";
    private Instant createdAt = Instant.now();
    private Instant resolvedAt;

    public String getId() {
        return id;
    }

    public void setId(String v) {
        id = v;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String v) {
        userId = v;
    }

    public String getEmailAnalysisId() {
        return emailAnalysisId;
    }

    public void setEmailAnalysisId(String v) {
        emailAnalysisId = v;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String v) {
        riskLevel = v;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String v) {
        title = v;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(List<String> v) {
        reasons = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String v) {
        status = v;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant v) {
        createdAt = v;
    }

    public Instant getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(Instant v) {
        resolvedAt = v;
    }
}