package com.example.gmailfrauddetection.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.*;

@Document("audit_logs")
public class AuditLog {
    @Id
    private String id;
    private String actor;
    private String action;
    private String resource;
    private String ipAddress;
    private final Instant timestamp = Instant.now();
    private final Map<String, Object> metadata = new LinkedHashMap<>();

    public AuditLog() {
    }

    public AuditLog(String actor, String action, String resource, String ipAddress) {
        this.actor = actor;
        this.action = action;
        this.resource = resource;
        this.ipAddress = ipAddress;
    }

    public String getId() {
        return id;
    }

    public String getActor() {
        return actor;
    }

    public String getAction() {
        return action;
    }

    public String getResource() {
        return resource;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}