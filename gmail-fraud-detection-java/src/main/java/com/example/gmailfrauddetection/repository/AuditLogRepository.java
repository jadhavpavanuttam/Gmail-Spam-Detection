package com.example.gmailfrauddetection.repository;

import com.example.gmailfrauddetection.document.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
    List<AuditLog> findTop50ByOrderByTimestampDesc();
}