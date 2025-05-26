package com.mailingsystem.mail.domain.repo;

import com.mailingsystem.mail.domain.entity.Mail;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailRepo {

    Mail save(Mail mail);
    Optional<Mail> findById(Long id);
}
