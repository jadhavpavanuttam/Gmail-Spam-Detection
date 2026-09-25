package com.example.gmailfrauddetection.ml;

import com.example.gmailfrauddetection.feature.EmailFeatureVector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MlPredictionService {
    private final String version;

    public MlPredictionService(@Value("${app.ml.model-version}") String v) {
        version = v;
    }

    /**
     * Java-native baseline logistic scoring. Coefficients are explicit and replaceable by trained serialized coefficients.
     */
    public MlPredictionResult predict(EmailFeatureVector f) {
        double z = -2.1 + 0.18 * f.urlCount() + 0.35 * f.hasHttpUrl() + 0.75 * f.suspiciousTld() + 1.0 * f.replyToMismatch() + 0.9 * f.authFailure() + 0.45 * f.urgentLanguage() + 0.35 * f.financialLanguage() + 0.85 * f.credentialLanguage() + 0.45 * f.suspiciousAttachment() + 0.2 * f.htmlContent() + 0.8 * f.linkTextMismatch() + 0.8 * f.domainSimilarity() + 0.7 * f.ipUrl() + 0.4 * f.shortener();
        double p = 1.0 / (1.0 + Math.exp(-z));
        p = Math.max(0, Math.min(1, p));
        return new MlPredictionResult(version, p, p >= .5 ? "PHISHING" : "BENIGN");
    }
}