package com.example.gmailfrauddetection.ml;

public record MlPredictionResult(String modelVersion, double fraudProbability, String label) {
}