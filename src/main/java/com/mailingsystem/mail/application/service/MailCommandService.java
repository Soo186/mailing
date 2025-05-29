package com.mailingsystem.mail.application.service;

import com.mailingsystem.mail.domain.entity.AddressBook;
import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.repo.AddressBookRepo;
import com.mailingsystem.mail.domain.repo.MailRepo;
import com.mailingsystem.mail.domain.service.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailCommandService {

    private final MailRepo mailRepo;
    private final MailSender mailSender;
    private final AddressBookRepo addressBookRepo;

    /**
     * 기존: 이메일 직접 입력해서 전송
     */
    @Async
    @Transactional
    public void sendMail(String to, String subject, String body) {
        Mail mail = Mail.create(to, subject, body);
        mailRepo.save(mail);

        boolean isSuccess = mailSender.send(mail);
        if (isSuccess) {
            mail.markAsSent();
        } else {
            mail.markAsFailed();
        }
    }

    /**
     * 추가: 주소록 ID 기반으로 전송
     */
    @Async
    @Transactional
    public void sendMailByAddressBookId(Long addressId, String subject, String body) {
        AddressBook address = addressBookRepo.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("주소록을 찾을 수 없습니다."));

        sendMail(address.getEmail(), subject, body); // 기존 로직 재사용
    }
}
