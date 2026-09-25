package com.example.gmailfrauddetection.controller;

import com.example.gmailfrauddetection.gmail.GoogleOAuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class OAuthController {
    private final GoogleOAuthService oauth;

    public OAuthController(GoogleOAuthService o) {
        oauth = o;
    }

    @GetMapping("/google")
    public String google(Authentication auth) {
        String url = auth.getName();

        oauth.authorizationUrl(url);
        System.out.println("GIVEN URL IS : " + url);
        return "<a href=\"" + oauth.authorizationUrl(url) + "\">Continue with Google</a>";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam String code, @RequestParam String state) {
        oauth.callback(code, state);
        return "Gmail connected callback received. Complete token exchange configuration if this is the first setup.";
    }
}