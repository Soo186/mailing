    package com.mailingsystem.mail.infra.service;

public interface GmailClient {
    boolean send(String to, String subject, String body);
    boolean isAvailable(); // 계정 상태 체크
}
