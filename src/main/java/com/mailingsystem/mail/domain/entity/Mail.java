package com.mailingsystem.mail.domain.entity;

import com.mailingsystem.mail.domain.enums.MailStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toAddress;

    private String subject;

    @Lob
    private String body;

    @Enumerated(EnumType.STRING)
    private MailStatus status;

    // 정적 팩토리 메서드 (생성 책임 부여)
    public static Mail create(String toAddress, String subject, String body) {
        Mail mail = new Mail();
        mail.toAddress = toAddress;
        mail.subject = subject;
        mail.body = body;
        mail.status = MailStatus.PENDING;
        return mail;
    }

    // 상태 전이 메서드 (도메인 로직)
    public void markAsSent() {

    }

    public void markAsFailed() {
    }
}
