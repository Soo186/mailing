package com.mailingsystem.mail.infra.repo;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.repo.MailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MailRepoImpl implements MailRepo {

    private final MailJpaRepo mailJpaRepo;

    @Override
    public Mail save(Mail mail) {
        return mailJpaRepo.save(mail);
    }

    @Override
    public Optional<Mail> findById(Long id) {
        return mailJpaRepo.findById(id);
    }
}
