package com.mailingsystem.mail.infra.service;


import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.mailingsystem.mail.infra.service.GmailMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GmailClientImpl implements GmailClient {

    private final Gmail gmail;

    @Override
    public boolean send(String to, String subject, String body) {
        try {
            log.info("메일 전송 시도 → {}", to);
            Message message = GmailMessageUtil.createMessage(to, subject, body);
            gmail.users().messages().send("me", message).execute();
            log.info("메일 전송 성공 → {}", to);
            return true;
        } catch (Exception e) {
            log.error("메일 전송 실패 → {}", to, e);
            return false;
        }
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
