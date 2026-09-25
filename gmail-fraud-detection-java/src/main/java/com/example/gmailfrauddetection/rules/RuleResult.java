package com.example.gmailfrauddetection.rules;

public record RuleResult(String ruleName, boolean triggered, double scoreContribution, String reason, String severity) {
}