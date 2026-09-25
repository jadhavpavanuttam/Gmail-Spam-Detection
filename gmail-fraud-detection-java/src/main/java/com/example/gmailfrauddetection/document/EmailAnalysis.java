package com.example.gmailfrauddetection.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.*;

@Document("email_analyses")
public class EmailAnalysis {
    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private String gmailMessageId;
    private String threadId, sender, senderDomain, replyTo, subject;
    @Indexed
    private Instant receivedAt;
    private List<String> urls = new ArrayList<>(), domains = new ArrayList<>(), reasons = new ArrayList<>(), labels = new ArrayList<>();
    private Map<String, Object> features = new LinkedHashMap<>();
    @Indexed
    private double ruleScore, mlScore, finalScore;
    @Indexed
    private String riskLevel, modelVersion;
    private Instant createdAt = Instant.now(), updatedAt = Instant.now();

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

    public String getGmailMessageId() {
        return gmailMessageId;
    }

    public void setGmailMessageId(String v) {
        gmailMessageId = v;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String v) {
        threadId = v;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String v) {
        sender = v;
    }

    public String getSenderDomain() {
        return senderDomain;
    }

    public void setSenderDomain(String v) {
        senderDomain = v;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String v) {
        replyTo = v;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String v) {
        subject = v;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Instant v) {
        receivedAt = v;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> v) {
        urls = v;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> v) {
        domains = v;
    }

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(List<String> v) {
        reasons = v;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> v) {
        labels = v;
    }

    public Map<String, Object> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Object> v) {
        features = v;
    }

    public double getRuleScore() {
        return ruleScore;
    }

    public void setRuleScore(double v) {
        ruleScore = v;
    }

    public double getMlScore() {
        return mlScore;
    }

    public void setMlScore(double v) {
        mlScore = v;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double v) {
        finalScore = v;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String v) {
        riskLevel = v;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String v) {
        modelVersion = v;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant v) {
        createdAt = v;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant v) {
        updatedAt = v;
    }
}