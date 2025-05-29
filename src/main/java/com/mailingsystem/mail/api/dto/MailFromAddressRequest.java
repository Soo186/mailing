package com.mailingsystem.mail.api.dto;

import lombok.Getter;

@Getter
public class MailFromAddressRequest {
    private Long addressId;
    private String subject;
    private String body;
}