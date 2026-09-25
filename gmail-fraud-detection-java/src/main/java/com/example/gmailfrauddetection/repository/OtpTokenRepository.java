package com.example.gmailfrauddetection.repository;

import com.example.gmailfrauddetection.document.OtpToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpTokenRepository extends MongoRepository<OtpToken, String> {
    Optional<OtpToken> findTopByUsernameOrderByLastSentAtDesc(String username);
}