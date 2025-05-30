package com.mailingsystem.mail.config;

import com.mailingsystem.mail.infra.service.GmailClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GmailClientConfig {

    private final GmailProperties gmailProperties;

    @Bean
    public List<GmailClientImpl> gmailClients() {
        return gmailProperties.getCredentials().stream()
                .map(GmailClientImpl::new)
                .toList();
    }
}
