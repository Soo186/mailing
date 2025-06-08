package com.mailingsystem.mail.api.dto;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.enums.MailStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailStatusResponse {
    private Long id;
    private MailStatus status;


    public static MailStatusResponse of(Mail mail) {
        return new MailStatusResponse(mail.getId(), mail.getStatus());
    }

}