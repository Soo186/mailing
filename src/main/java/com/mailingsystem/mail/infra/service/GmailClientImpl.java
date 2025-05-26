package com.mailingsystem.mail.infra.service;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.service.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GmailClientImpl implements MailSender {

    @Override
    public boolean send(Mail mail) {
        try {
            // TODO: 여기에 실제 Gmail API 연동 로직 작성
            log.info("Sending email to {}", mail.getToAddress());
            return true;
        } catch (Exception e) {
            log.error("메일 전송 실패: {}", e.getMessage());
            return false;
        }
    }
}
