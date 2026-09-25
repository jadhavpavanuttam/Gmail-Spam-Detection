package com.example.gmailfrauddetection.rules;

import com.example.gmailfrauddetection.feature.EmailFeatureVector;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RuleEngineService {
    private final List<FraudRule> rules = List.of(
            f -> new RuleResult("SuspiciousUrlRule", f.urlCount() > 0 && f.hasHttpUrl() > 0, .12, "HTTP URL detected; treat as a risk signal, not proof of fraud.", "MEDIUM"),
            f -> new RuleResult("SuspiciousTldRule", f.suspiciousTld() > 0, .10, "Suspicious or uncommon top-level domain signal.", "MEDIUM"),
            f -> new RuleResult("ReplyToMismatchRule", f.replyToMismatch() > 0, .18, "Reply-To domain differs from sender domain.", "HIGH"),
            f -> new RuleResult("AuthenticationFailureRule", f.authFailure() > 0, .18, "Available authentication results indicate a failure.", "HIGH"),
            f -> new RuleResult("UrgentLanguageRule", f.urgentLanguage() > 0, .08, "Urgent language detected.", "LOW"),
            f -> new RuleResult("FinancialLanguageRule", f.financialLanguage() > 0, .08, "Financial language detected.", "LOW"),
            f -> new RuleResult("CredentialRequestRule", f.credentialLanguage() > 0, .14, "Credential-related request language detected.", "HIGH"),
            f -> new RuleResult("SuspiciousAttachmentRule", f.suspiciousAttachment() > 0, .12, "Suspicious attachment metadata detected.", "MEDIUM"),
            f -> new RuleResult("LinkMismatchRule", f.linkTextMismatch() > 0, .16, "Visible link text does not match the destination domain.", "HIGH"),
            f -> new RuleResult("LookalikeDomainRule", f.domainSimilarity() > 0, .16, "Domain similarity/look-alike signal detected.", "HIGH"),
            f -> new RuleResult("IpUrlRule", f.ipUrl() > 0, .10, "URL uses a raw IP address.", "MEDIUM"),
            f -> new RuleResult("UrlShortenerRule", f.shortener() > 0, .08, "URL shortener detected.", "LOW")
    );

    public RuleResultSet evaluate(EmailFeatureVector f) {
        double score = 0;
        List<RuleResult> hit = new ArrayList<>();
        for (FraudRule r : rules) {
            var x = r.evaluate(f);
            if (x.triggered()) {
                score += x.scoreContribution();
                hit.add(x);
            }
        }
        return new RuleResultSet(Math.min(1, score), hit);
    }
}
