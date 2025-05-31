package com.mailingsystem.mail.application.service;


import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.repo.MailRepo;
import com.mailingsystem.mail.domain.service.MailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
@RequiredArgsConstructor
public class MailAsyncExecutor {

    private final MailRepo mailRepo;
    private final MailSender mailSender;

    @Async
    @Transactional
    public void send(String to, String subject, String body) {

        log.info("비동기 시작 Sending mail to: {}, subject: {}", to, subject);

        Mail mail = Mail.create(to, subject, body);
        mailRepo.save(mail);

        boolean isSuccess = mailSender.send(mail);
        if (isSuccess) {
            mail.markAsSent();
        } else {
            mail.markAsFailed();
        }
        mailRepo.save(mail);

        log.info("비동기 완료 Mail sent to: {}, status: {}", to, mail.getStatus());
    }
}
