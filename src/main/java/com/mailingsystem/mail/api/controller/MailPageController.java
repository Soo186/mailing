package com.mailingsystem.mail.api.controller;

import com.mailingsystem.mail.api.dto.MailRequest;
import com.mailingsystem.mail.api.dto.MailStatusResponse;
import com.mailingsystem.mail.application.service.MailCommandService;
import com.mailingsystem.mail.application.service.MailQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mails")
public class MailPageController {

    private final MailCommandService mailCommandService;
    private final MailQueryService mailQueryService;
    @GetMapping("/form")
    public String mailForm(Model model) {
        model.addAttribute("mailRequest", new MailRequest());
        return "mail-form";
    }
    @PostMapping("/send")
    public String sendMail(@ModelAttribute MailRequest mailRequest) {
        log.info("폼 입력 확인: to={}, subject={}, body={}",
                mailRequest.getTo(), mailRequest.getSubject(), mailRequest.getBody());

        Long mailId = mailCommandService.sendAndReturnId(mailRequest);
        return "redirect:/mails/" + mailId + "/result";
    }


    @GetMapping("/{id}/result")
    public String result(@PathVariable Long id, Model model) {
        MailStatusResponse status = mailQueryService.getStatus(id);
        model.addAttribute("status", status);
        return "mail-result";
    }

}
