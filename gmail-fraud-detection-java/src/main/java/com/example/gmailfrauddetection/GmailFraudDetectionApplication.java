package com.example.gmailfrauddetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableScheduling
@EnableAsync

public class GmailFraudDetectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(GmailFraudDetectionApplication.class, args);

    }
}
