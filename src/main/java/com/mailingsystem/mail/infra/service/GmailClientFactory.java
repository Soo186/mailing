package com.mailingsystem.mail.infra.service;

import org.springframework.stereotype.Component;

@Component
public class GmailClientFactory {
    public GmailClient create(String credential) {
        return new GmailClientImpl(credential);
    }
}
