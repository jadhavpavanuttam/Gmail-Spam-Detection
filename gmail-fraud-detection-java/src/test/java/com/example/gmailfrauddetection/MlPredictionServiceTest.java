package com.example.gmailfrauddetection;

import com.example.gmailfrauddetection.feature.EmailFeatureVector;
import com.example.gmailfrauddetection.ml.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MlPredictionServiceTest {
    @Test
    void probabilityRange() {
        var s = new MlPredictionService("v1");
        var f = new EmailFeatureVector(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertTrue(s.predict(f).fraudProbability() >= 0 && s.predict(f).fraudProbability() <= 1);
    }
}
