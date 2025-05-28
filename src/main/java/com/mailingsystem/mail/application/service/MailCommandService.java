package com.mailingsystem.mail.application.service;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.repo.MailRepo;
import com.mailingsystem.mail.domain.service.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailCommandService {

    private final MailRepo mailRepo;
    private final MailSender mailSender;

    @Async
    @Transactional
    public void sendMail(String to, String subject, String body) {
        // 1. 메일 객체 생성
        Mail mail = Mail.create(to, subject, body);

        // 2. DB 저장
        mailRepo.save(mail);

        // 3. 메일 전송
        boolean isSuccess = mailSender.send(mail);

        // 4. 상태 변경
        if (isSuccess) {
            mail.markAsSent();
        } else {
            mail.markAsFailed();
        }

        // 5. 상태 변경 사항 저장
    }
}