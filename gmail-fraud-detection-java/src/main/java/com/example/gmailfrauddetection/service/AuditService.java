package com.example.gmailfrauddetection.service;

import com.example.gmailfrauddetection.document.AuditLog;
import com.example.gmailfrauddetection.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditLogRepository repo;

    public AuditService(AuditLogRepository r) {
        repo = r;
    }

    public void log(String actor, String action, String resource) {
        repo.save(new AuditLog(actor, action, resource, null));
    }
}
