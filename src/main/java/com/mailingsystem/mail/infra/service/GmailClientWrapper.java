package com.mailingsystem.mail.infra.service;

import com.google.api.services.gmail.Gmail;
import lombok.Getter;

@Getter
public class GmailClientWrapper implements GmailClient {

    private final GmailClient delegate;
    private final String credentialName;

    private int failureCount = 0;
    private long lastFailureTime = 0L;

    public GmailClientWrapper(GmailClient delegate, String credentialName) {
        this.delegate = delegate;
        this.credentialName = credentialName;
    }

    @Override
    public boolean send(String to, String subject, String body) {
        try {
            boolean result = delegate.send(to, subject, body);
            if (result) {
                resetFailure();
            } else {
                recordFailure();
            }
            return result;
        } catch (Exception e) {
            recordFailure();
            return false;
        }
    }

    @Override
    public boolean isAvailable() {
        return isHealthy() && delegate.isAvailable();
    }

    public boolean isHealthy() {
        long now = System.currentTimeMillis();
        return failureCount < 3 || (now - lastFailureTime > 10_000);
    }

    public void recordFailure() {
        failureCount++;
        lastFailureTime = System.currentTimeMillis();
    }

    public void resetFailure() {
        failureCount = 0;
        lastFailureTime = 0L;
    }

}
