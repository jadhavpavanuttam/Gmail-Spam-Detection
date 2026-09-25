package com.example.gmailfrauddetection.dto;

import java.util.*;

public final class AnalysisDtos {
    private AnalysisDtos() {
    }

    public record RiskResponse(double mlScore, double ruleScore, double finalScore, String riskLevel,
                               List<String> reasons, String modelVersion) {
    }

    public record StatusRequest(String status) {
    }
}