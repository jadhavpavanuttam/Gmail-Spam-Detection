package com.example.gmailfrauddetection.rules;

import com.example.gmailfrauddetection.feature.EmailFeatureVector;

public interface FraudRule {
    RuleResult evaluate(EmailFeatureVector f);
}