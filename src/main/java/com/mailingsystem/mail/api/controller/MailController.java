package com.mailingsystem.mail.api.controller;

import com.mailingsystem.mail.api.dto.MailRequest;
import com.mailingsystem.mail.application.service.MailCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mails")
@RequiredArgsConstructor
public class MailController {

    private final MailCommandService mailCommandService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody MailRequest request) {
        mailCommandService.sendMail(
                request.getTo(),
                request.getSubject(),
                request.getBody()
        );
        return ResponseEntity.ok().build();
    }
}
