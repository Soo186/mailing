package com.mailingsystem.mail.api.dto;

import com.mailingsystem.mail.domain.entity.AddressBook;
import lombok.Getter;

@Getter
public class AddressBookResponse {

    private final Long id;
    private final String name;
    private final String email;

    public AddressBookResponse(AddressBook addressBook) {
        this.id = addressBook.getId();
        this.name = addressBook.getName();
        this.email = addressBook.getEmail();
    }
}
