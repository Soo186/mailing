package com.mailingsystem.mail.api.dto;

import com.mailingsystem.mail.domain.enums.MailStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailStatusResponse {
    private Long id;
    private MailStatus status;
}