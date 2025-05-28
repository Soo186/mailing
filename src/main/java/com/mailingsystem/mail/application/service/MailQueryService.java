package com.mailingsystem.mail.application.service;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.repo.MailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailQueryService {

    private final MailRepo mailRepo;

    @Transactional(readOnly = true)
    public Mail getMail(Long id) {
        return mailRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메일이 존재하지 않습니다."));
    }
}
