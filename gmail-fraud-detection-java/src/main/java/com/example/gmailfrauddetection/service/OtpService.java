package com.example.gmailfrauddetection.service;

import com.example.gmailfrauddetection.document.*;
import com.example.gmailfrauddetection.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.*;

@Service
public class OtpService {
    private final OtpTokenRepository repo;
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JavaMailSender mail;
    private final int minutes, maxAttempts, cooldown;
    private final SecureRandom random = new SecureRandom();

    public OtpService(OtpTokenRepository r, UserRepository u, PasswordEncoder e, JavaMailSender m, @Value("${app.otp.expiration-minutes}") int min, @Value("${app.otp.max-attempts}") int max, @Value("${app.otp.resend-seconds}") int cd) {
        repo = r;
        users = u;
        encoder = e;
        mail = m;
        minutes = min;
        maxAttempts = max;
        cooldown = cd;
    }

    public void send(User u) {
        var old = repo.findTopByUsernameOrderByLastSentAtDesc(u.getUsername());
        if (old.isPresent() && old.get().getLastSentAt() != null && old.get().getLastSentAt().plusSeconds(cooldown).isAfter(Instant.now()))
            throw new IllegalArgumentException("Please wait before requesting another OTP");
        String code = String.format("%06d", random.nextInt(1_000_000));
        OtpToken t = new OtpToken();
        t.setUsername(u.getUsername());
        t.setOtpHash(encoder.encode(code));
        t.setExpiresAt(Instant.now().plus(minutes, java.time.temporal.ChronoUnit.MINUTES));
        t.setLastSentAt(Instant.now());
        repo.save(t);
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(u.getGmailAddress());
            msg.setSubject("Gmail Fraud Detection OTP");
            msg.setText("Your verification OTP is: " + code + "\\nIt expires in " + minutes + " minutes.");
            mail.send(msg);
        } catch (Exception ex) {
            System.err.println("SMTP not configured; development OTP: " + code);
        }
    }

    public void verify(String username, String code) {
        OtpToken t = repo.findTopByUsernameOrderByLastSentAtDesc(username).orElseThrow(() -> new IllegalArgumentException("OTP not found"));
        if (t.isUsed() || t.getExpiresAt().isBefore(Instant.now()))
            throw new IllegalArgumentException("OTP expired or already used");
        if (t.getAttempts() >= maxAttempts) throw new IllegalArgumentException("Maximum OTP attempts exceeded");
        t.setAttempts(t.getAttempts() + 1);
        if (!encoder.matches(code, t.getOtpHash())) {
            repo.save(t);
            throw new IllegalArgumentException("Invalid OTP");
        }
        t.setUsed(true);
        repo.save(t);
        User u = users.findByUsername(username).orElseThrow();
        u.setGmailOwnershipVerified(true);
        users.save(u);
    }
}