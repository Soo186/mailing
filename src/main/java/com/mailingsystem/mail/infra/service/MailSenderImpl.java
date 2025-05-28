package com.mailingsystem.mail.infra.service;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.service.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {

    private final GmailClientManager manager;

    @Override
    public boolean send(Mail mail) {
        return manager.getAvailableClient()
                .map(client -> client.send(
                        mail.getToAddress(),
                        mail.getSubject(),
                        mail.getBody()
                ))
                .orElse(false);
    }
}
