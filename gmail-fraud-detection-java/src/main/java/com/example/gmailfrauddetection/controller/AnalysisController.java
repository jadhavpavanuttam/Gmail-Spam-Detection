package com.example.gmailfrauddetection.controller;

import com.example.gmailfrauddetection.dto.AnalysisDtos.RiskResponse;
import com.example.gmailfrauddetection.feature.EmailFeatureVector;
import com.example.gmailfrauddetection.risk.RiskScoringService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class AnalysisController {
    private final RiskScoringService risk;

    public AnalysisController(RiskScoringService r) {
        risk = r;
    }

    @PostMapping("/demo/analyze")
    public RiskResponse demo(@RequestBody EmailInput in) {
        var f = new EmailFeatureVector(in.urlCount(), in.uniqueDomainCount(), in.hasHttpUrl(), in.suspiciousTld(), in.replyToMismatch(), in.authFailure(), in.urgentLanguage(), in.financialLanguage(), in.credentialLanguage(), in.suspiciousAttachment(), in.htmlContent(), in.linkTextMismatch(), in.domainSimilarity(), in.ipUrl(), in.shortener());
        return risk.score(f);
    }

    record EmailInput(double urlCount, double uniqueDomainCount, double hasHttpUrl, double suspiciousTld,
                      double replyToMismatch, double authFailure, double urgentLanguage, double financialLanguage,
                      double credentialLanguage, double suspiciousAttachment, double htmlContent,
                      double linkTextMismatch, double domainSimilarity, double ipUrl, double shortener) {
    }
}