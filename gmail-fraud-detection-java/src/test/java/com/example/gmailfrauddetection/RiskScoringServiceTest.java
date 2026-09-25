package com.example.gmailfrauddetection;
import com.example.gmailfrauddetection.feature.EmailFeatureVector;import com.example.gmailfrauddetection.ml.MlPredictionService;import com.example.gmailfrauddetection.risk.RiskScoringService;import com.example.gmailfrauddetection.rules.RuleEngineService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
class RiskScoringServiceTest {
 @Test void scoreIsBounded(){var s=new RiskScoringService(new MlPredictionService("java-phishing-lr-v1"),new RuleEngineService(),.55,.45);var f=new EmailFeatureVector(2,2,1,1,1,1,1,1,1,1,1,0,1,1,1);var r=s.score(f);assertTrue(r.finalScore()>=0&&r.finalScore()<=1);assertEquals("java-phishing-lr-v1",r.modelVersion());}
}