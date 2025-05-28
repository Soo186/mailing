package com.mailingsystem.mail.api.dto;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.enums.MailStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MailResponse {

    private Long id;
    private String to;
    private String subject;
    private String body;
    private MailStatus status;

    public MailResponse(Mail mail) {
        this.id = mail.getId();
        this.to = mail.getToAddress();
        this.subject = mail.getSubject();
        this.body = mail.getBody();
        this.status = mail.getStatus();
    }

}
