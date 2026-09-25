package com.example.gmailfrauddetection.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<?> bad(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("timestamp", Instant.now(), "status", 400, "error", "VALIDATION_ERROR", "message", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> any(Exception e) {
        return ResponseEntity.status(500).body(Map.of("timestamp", Instant.now(), "status", 500, "error", "INTERNAL_ERROR", "message", e.getMessage() == null ? "Internal server error" : e.getMessage()));
    }
}