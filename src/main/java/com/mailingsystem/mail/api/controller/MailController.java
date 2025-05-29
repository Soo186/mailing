package com.mailingsystem.mail.api.controller;

import com.mailingsystem.mail.api.dto.MailFromAddressRequest;
import com.mailingsystem.mail.api.dto.MailRequest;
import com.mailingsystem.mail.api.dto.MailResponse;
import com.mailingsystem.mail.api.dto.MailStatusResponse;
import com.mailingsystem.mail.application.service.MailCommandService;
import com.mailingsystem.mail.application.service.MailQueryService;
import com.mailingsystem.mail.domain.entity.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mails")
@RequiredArgsConstructor
public class MailController {

    private final MailCommandService mailCommandService;
    private final MailQueryService mailQueryService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody MailRequest request) {
        mailCommandService.sendMail(
                request.getTo(),
                request.getSubject(),
                request.getBody()
        );
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MailResponse> getMailStatus(@PathVariable Long id) {
        Mail mail = mailQueryService.getMail(id);
        return ResponseEntity.ok(new MailResponse(mail));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<MailStatusResponse> getMailStatusOnly(@PathVariable Long id) {
        Mail mail = mailQueryService.getMail(id);
        return ResponseEntity.ok(new MailStatusResponse(mail.getId(), mail.getStatus()));
    }

    @PostMapping("/address-book")
    public ResponseEntity<Void> sendMailByAddress(@RequestBody MailFromAddressRequest request) {
        mailCommandService.sendMailByAddressBookId(
                request.getAddressId(),
                request.getSubject(),
                request.getBody()
        );
        return ResponseEntity.accepted().build();
    }

}
