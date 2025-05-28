package com.mailingsystem.mail.infra.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GmailClientImpl implements GmailClient {

    private final String credential; // Gmail 계정 or key 파일


    @Override
    public boolean send(String to, String subject, String body) {
        // 실제 Gmail API 호출
        log.info("메일 전송 시도: {}", credential);
        return true;
    }

    @Override
    public boolean isAvailable() {
        return true; // 나중에 실패 상태 저장 시 false 반환
    }
}
