package com.example.gmailfrauddetection.repository;

import com.example.gmailfrauddetection.document.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

public interface AlertRepository extends MongoRepository<Alert, String> {
    List<Alert> findByUserIdOrderByCreatedAtDesc(String u);

    long countByUserIdAndStatus(String u, String s);
}