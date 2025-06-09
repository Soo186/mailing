package com.mailingsystem.mail.infra.service;

import com.mailingsystem.mail.config.GmailProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GmailClientManager {

    private final GmailClientFactory factory;
    private final GmailProperties gmailProperties;
    private final List<GmailClient> clients = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (String credential : gmailProperties.getCredentials()) {
            clients.add(factory.create(credential));
        }
    }

    public Optional<GmailClient> getAvailableClient() {
        return clients.stream()
                .filter(GmailClient::isAvailable)
                .findFirst();
    }
}
