package com.example.gmailfrauddetection.risk;

import com.example.gmailfrauddetection.dto.AnalysisDtos.RiskResponse;
import com.example.gmailfrauddetection.ml.*;
import com.example.gmailfrauddetection.rules.*;
import com.example.gmailfrauddetection.feature.EmailFeatureVector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RiskScoringService {
    private final double mw;
    private final double rw;
    private final MlPredictionService ml;
    private final RuleEngineService rules;

    public RiskScoringService(MlPredictionService m, RuleEngineService r, @Value("${app.risk.ml-weight}") double mw, @Value("${app.risk.rule-weight}") double rw) {
        ml = m;
        rules = r;
        this.mw = mw;
        this.rw = rw;
    }

    public RiskResponse score(EmailFeatureVector f) {
        var m = ml.predict(f);
        var rr = rules.evaluate(f);
        double finalScore = Math.max(0, Math.min(1, m.fraudProbability() * mw + rr.score() * rw));
        String level = finalScore < .30 ? "LOW" : finalScore < .60 ? "MEDIUM" : finalScore < .80 ? "HIGH" : "CRITICAL";
        List<String> reasons = rr.results().stream().map(RuleResult::reason).toList();
        return new RiskResponse(m.fraudProbability(), rr.score(), finalScore, level, reasons, m.modelVersion());
    }
}