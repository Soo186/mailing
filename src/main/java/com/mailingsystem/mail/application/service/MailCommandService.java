package com.mailingsystem.mail.application.service;

import com.mailingsystem.mail.domain.entity.AddressBook;
import com.mailingsystem.mail.domain.repo.AddressBookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailCommandService {

    private final AddressBookRepo addressBookRepo;
    private final MailAsyncExecutor mailAsyncExecutor;

    // 직접 입력해서 보내는 경우
    public void sendMail(String to, String subject, String body) {
        mailAsyncExecutor.send(to, subject, body);
    }

    // 주소록 ID 기반 전송
    public void sendMailByAddressBookId(Long addressId, String subject, String body) {
        AddressBook address = addressBookRepo.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("주소록을 찾을 수 없습니다."));
        mailAsyncExecutor.send(address.getEmail(), subject, body); // ✅ 외부 Bean 호출로 비동기 OK
    }
}
