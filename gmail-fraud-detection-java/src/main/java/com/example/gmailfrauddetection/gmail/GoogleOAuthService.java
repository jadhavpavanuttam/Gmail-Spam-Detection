package com.example.gmailfrauddetection.gmail;

import com.example.gmailfrauddetection.document.User;
import com.example.gmailfrauddetection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


@Service

public class GoogleOAuthService {
    private final UserRepository users;
    private boolean enabled;
    private String clientId, redirectUri, scopes;

    public GoogleOAuthService(UserRepository u, @Value("${google.oauth-enabled}") boolean e, @Value("${google.client-id}") String c, @Value("${google.redirect-uri}") String r, @Value("${google.scopes}") String s) {
        users = u;
        enabled = e;
        clientId = c;
        redirectUri = r;
        scopes = s;
    }

    public String authorizationUrl(String username) {
        if (!enabled)
            throw new IllegalStateException("Google OAuth is disabled. Set GOOGLE_OAUTH_ENABLED=true and configure Google credentials.");
        if (clientId.isBlank()) throw new IllegalStateException("GOOGLE_CLIENT_ID is missing");
        String state = username + "." + UUID.randomUUID();

        return UriComponentsBuilder.fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .queryParam("scope", scopes)
                .queryParam("access_type", "offline")
                .queryParam("prompt", "consent")
                .queryParam("state", URLEncoder.encode(state, StandardCharsets.UTF_8))
                .build()
                .toUriString();
    }

    public void callback(String code, String state) {
        if (code == null || code.isBlank())
            throw new IllegalArgumentException("Authorization code missing");/* Exchange code with Google's token endpoint in production credentials-enabled flow. */
    }
}