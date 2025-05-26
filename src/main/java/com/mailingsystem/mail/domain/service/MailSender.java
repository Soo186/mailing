package com.mailingsystem.mail.domain.service;

import com.mailingsystem.mail.domain.entity.Mail;
import org.springframework.stereotype.Service;

@Service
public interface MailSender {
    boolean send(Mail mail);
}
