package com.mailingsystem.mail.config;

import com.mailingsystem.mail.infra.service.GmailClient;
import com.mailingsystem.mail.infra.service.GmailClientFactory;
import com.mailingsystem.mail.infra.service.GmailClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GmailClientConfig {

    private final GmailProperties gmailProperties;
    private final GmailClientFactory gmailClientFactory;

    @Bean
    public List<GmailClient> gmailClients() {
        return gmailProperties.getCredentials().stream()
                .map(gmailClientFactory::create)
                .toList();
    }
}
