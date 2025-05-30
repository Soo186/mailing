package com.mailingsystem.mail.domain.repo;

import com.mailingsystem.mail.domain.entity.Mail;

import java.util.Optional;


public interface MailRepo {
    Mail save(Mail mail);
    Optional<Mail> findById(Long id);
}
