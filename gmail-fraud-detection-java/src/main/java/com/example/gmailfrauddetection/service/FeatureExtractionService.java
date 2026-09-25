package com.example.gmailfrauddetection.service;

import com.example.gmailfrauddetection.feature.EmailFeatureVector;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.*;

@Service
public class FeatureExtractionService {
    private static final Pattern URL = Pattern.compile("(?i)\\bhttps?://[^\\s<>\"']+");
    private static final Pattern IP = Pattern.compile("https?://(?:\\d{1,3}\\.){3}\\d{1,3}");

    public EmailFeatureVector extract(String text, String sender, String replyTo, Map<String, String> headers, int attachments) {
        String s = text == null ? "" : text;
        List<String> urls = new ArrayList<>();
        var m = URL.matcher(s);
        while (m.find()) urls.add(m.group());
        Set<String> domains = new HashSet<>();
        for (String u : urls) {
            try {
                domains.add(java.net.URI.create(u).getHost());
            } catch (Exception ignored) {
            }
        }
        String lower = s.toLowerCase();
        double urgent = count(lower, List.of("urgent", "immediately", "act now", "verify now"));
        double financial = count(lower, List.of("payment", "invoice", "bank", "wire", "refund"));
        double cred = count(lower, List.of("password", "login", "credential", "verify your account"));
        String fromDomain = domain(sender), replyDomain = domain(replyTo);
        double mismatch = fromDomain != null && replyDomain != null && !fromDomain.equalsIgnoreCase(replyDomain) ? 1 : 0;
        String auth = headers.getOrDefault("authentication-results", "").toLowerCase();
        double authFail = (auth.contains("spf=fail") || auth.contains("dkim=fail") || auth.contains("dmarc=fail")) ? 1 : 0;
        double tld = domains.stream().anyMatch(d -> d != null && d.matches(".*\\.(zip|top|click|work|xyz|icu)$")) ? 1 : 0;
        double ip = urls.stream().anyMatch(x -> IP.matcher(x).find()) ? 1 : 0;
        double shortener = urls.stream().anyMatch(x -> x.matches("(?i).*https?://(bit\\.ly|tinyurl\\.com|t\\.co|goo\\.gl)/.*")) ? 1 : 0;
        return new EmailFeatureVector(urls.size(), domains.size(), urls.stream().anyMatch(x -> x.startsWith("http://")) ? 1 : 0, tld, mismatch, authFail, urgent, financial, cred, attachments > 0 ? 1 : 0, s.contains("<") ? 1 : 0, 0, 0, ip, shortener);
    }

    private double count(String s, List<String> terms) {
        return terms.stream().filter(s::contains).count() > 0 ? 1 : 0;
    }

    private String domain(String e) {
        if (e == null) return null;
        int a = e.lastIndexOf('@');
        return a >= 0 ? e.substring(a + 1).replace(">", "").trim() : null;
    }
}