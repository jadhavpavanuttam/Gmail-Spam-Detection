package com.example.gmailfrauddetection.rules;

import java.util.*;

public record RuleResultSet(double score, List<RuleResult> results) {
}