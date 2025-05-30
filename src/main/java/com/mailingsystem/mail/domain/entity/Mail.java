package com.mailingsystem.mail.domain.entity;

import com.mailingsystem.mail.domain.enums.MailStatus;
import lombok.Getter;

@Getter
public class Mail {

    private Long id;
    private String toAddress;
    private String subject;
    private String body;
    private MailStatus status;

    // 정적 팩토리 메서드
    public static Mail create(String toAddress, String subject, String body) {
        Mail mail = new Mail();
        mail.toAddress = toAddress;
        mail.subject = subject;
        mail.body = body;
        mail.status = MailStatus.PENDING;
        return mail;
    }

    // 조회용 정적 팩토리
    public static Mail restore(Long id, String to, String subject, String body, MailStatus status) {
        Mail mail = new Mail();
        mail.id = id;
        mail.toAddress = to;
        mail.subject = subject;
        mail.body = body;
        mail.status = status;
        return mail;
    }

    // 상태 전이
    public void markAsSent() {
        this.status = MailStatus.SENT;
    }

    public void markAsFailed() {
        this.status = MailStatus.FAILED;
    }
}
