# REAL-TIME GMAIL FRAUD / PHISHING EMAIL DETECTION — Java Full Stack

This repository is a Java/Spring Boot implementation scaffold for an independent, explainable Gmail security analysis layer.

## Hard scope
- Java 17 + Spring Boot
- MongoDB
- Spring Security + BCrypt + JWT
- Gmail API/OAuth integration adapter
- MIME/URL/header analysis components
- Explainable Strategy-style rule engine
- Java-native ML baseline
- HTML/CSS/JavaScript frontend
- Docker + Swagger + JUnit

**No Python, FastAPI, Flask, Node.js backend, or separate Python ML server is used.**

## Important status
The project is deliberately safe to run without external credentials. Live Gmail OAuth/token exchange requires Google Cloud credentials and must be completed before claiming live Gmail synchronization works. SMTP is also external. The demo analysis endpoint is available after login so the risk engine can be demonstrated locally.

## Run in IntelliJ
1. Install JDK 17.
2. Start MongoDB: `docker compose up -d mongodb`
3. Open this folder as a Maven project.
4. Set environment variables from `.env.example`.
5. Run `GmailFraudDetectionApplication`.
6. Open `http://localhost:8080`.
7. Swagger: `http://localhost:8080/swagger-ui/index.html`.
8. Health: `http://localhost:8080/actuator/health`.

## Google OAuth
Create a Google Cloud project, enable Gmail API, configure an OAuth consent screen, create a Web application OAuth client, and add:
`http://localhost:8080/oauth/callback`
as an authorized redirect URI. Put client ID/secret into environment variables and set `GOOGLE_OAUTH_ENABLED=true`.

Use the minimum required scope:
`https://www.googleapis.com/auth/gmail.readonly`

The entered Gmail address must be checked against the authenticated Google profile before storing a connection. Do not store credentials in source control.

## Risk engine
`finalScore = 0.55 * mlScore + 0.45 * ruleScore`.
Weights and thresholds are configuration parameters, not universal truth. They must be experimentally validated against an appropriate dataset.

## Java ML
The included baseline is a Java-native logistic scoring implementation with an explicit feature vector. This makes the repository runnable without Python. For research evaluation, replace the coefficients with coefficients trained from a documented public dataset and persist a model artifact/version under `ml-model/`.

## Security
Never log/store passwords, OTP values, OAuth client secrets, access/refresh tokens, JWTs, or full private email bodies.

## Research limitation
This application is not Gmail's internal spam/phishing classifier and cannot access Google's internal anti-abuse engine. It provides an additional analysis layer for an explicitly authorized mailbox.

## Suggested implementation phases
1. Auth/JWT/Mongo
2. OTP/audit
3. Google OAuth/Gmail
4. MIME/header/URL features
5. Rule engine
6. Java ML training/evaluation
7. Hybrid scoring
8. History-based incremental sync
9. Alerts/dashboard
10. Tests/Docker/Swagger/docs
