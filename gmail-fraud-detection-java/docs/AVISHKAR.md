# Avishkar Research Presentation Guide

## Problem
Phishing emails can combine social engineering, suspicious links, authentication anomalies, look-alike domains and attachment signals. The project studies an independent, explainable analysis layer.

## Proposed contribution
A Java/Spring Boot pipeline that retrieves authorized Gmail data, extracts evidence, executes deterministic rules, applies a Java ML baseline, and combines both into a configurable risk score.

## Key methodology
Email acquisition -> MIME/header parsing -> feature extraction -> rule engine + Java ML -> hybrid scoring -> explanation -> MongoDB -> alerts/dashboard.

## Important boundary
The system does not replace or access Gmail's internal spam/anti-abuse mechanisms.

## Demonstration
Register -> OTP -> login -> configure OAuth -> connect Gmail -> sync -> analyze -> inspect score/reasons -> dashboard/alerts.

## Evaluation
Report precision, recall, F1, ROC-AUC, confusion matrix and class distribution. Explicitly discuss false positives/negatives and dataset limitations. Do not claim perfect performance from a small demonstration set.

## Research questions
- How does an explainable rule layer complement a statistical classifier?
- Which email features contribute most to risk?
- How do rule/ML weights affect false positives and false negatives?
- How robust is the system across different phishing corpora?
