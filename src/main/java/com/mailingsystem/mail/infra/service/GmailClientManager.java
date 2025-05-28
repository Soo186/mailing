package com.mailingsystem.mail.infra.service;

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

    private final List<GmailClient> clients = new ArrayList<>();

    @PostConstruct
    public void init() {
        clients.add(factory.create("cred1.json"));
        clients.add(factory.create("cred2.json"));
        // 필요 시 더 추가
    }

    public Optional<GmailClient> getAvailableClient() {
        return clients.stream()
                .filter(GmailClient::isAvailable)
                .findFirst();
    }
}
