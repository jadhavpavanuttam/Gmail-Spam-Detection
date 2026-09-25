package com.example.gmailfrauddetection.repository;

import com.example.gmailfrauddetection.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByGmailAddress(String gmailAddress);

    boolean existsByUsername(String username);

    boolean existsByGmailAddress(String gmailAddress);
}