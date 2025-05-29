package com.mailingsystem.mail.application.service;

import com.mailingsystem.mail.domain.entity.AddressBook;
import com.mailingsystem.mail.domain.repo.AddressBookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressBookQueryService {

    private final AddressBookRepo addressBookRepo;

    public List<AddressBook> getAllAddresses() {
        return addressBookRepo.findAll();
    }
}
