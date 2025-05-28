package com.mailingsystem.mail.domain.service;

import com.mailingsystem.mail.domain.entity.Mail;



public interface MailSender {
    boolean send(Mail mail);
}
