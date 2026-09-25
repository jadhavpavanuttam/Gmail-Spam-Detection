package com.example.gmailfrauddetection.repository;

import com.example.gmailfrauddetection.document.EmailAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;

public interface EmailAnalysisRepository extends MongoRepository<EmailAnalysis, String> {
    Optional<EmailAnalysis> findByUserIdAndGmailMessageId(String u, String g);

    List<EmailAnalysis> findTop20ByUserIdOrderByReceivedAtDesc(String u);

    long countByUserId(String u);

    long countByUserIdAndRiskLevel(String u, String r);

    List<EmailAnalysis> findTop10ByUserIdOrderByFinalScoreDesc(String u);
}