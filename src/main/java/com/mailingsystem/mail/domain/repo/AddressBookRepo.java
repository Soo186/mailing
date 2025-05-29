package com.mailingsystem.mail.domain.repo;

import com.mailingsystem.mail.domain.entity.AddressBook;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepo {

    AddressBook save(AddressBook addressBook);

    Optional<AddressBook> findById(Long id);

    List<AddressBook> findAll();
}
