package com.mailingsystem.mail.application.service;

import com.mailingsystem.mail.domain.entity.AddressBook;
import com.mailingsystem.mail.domain.repo.AddressBookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressBookCommandService  {

    private final AddressBookRepo addressBookRepo;

    @Transactional
    public Long register(String name, String email) {
        AddressBook address = AddressBook.create(name, email);
        AddressBook saved = addressBookRepo.save(address);
        return saved.getId();
    }
}
