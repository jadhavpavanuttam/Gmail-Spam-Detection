package com.example.gmailfrauddetection.feature;

public record EmailFeatureVector(double urlCount, double uniqueDomainCount, double hasHttpUrl, double suspiciousTld,
                                 double replyToMismatch, double authFailure, double urgentLanguage,
                                 double financialLanguage, double credentialLanguage, double suspiciousAttachment,
                                 double htmlContent, double linkTextMismatch, double domainSimilarity, double ipUrl,
                                 double shortener) {
}