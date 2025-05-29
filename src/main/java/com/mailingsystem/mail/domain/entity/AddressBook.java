package com.mailingsystem.mail.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 수신자 이름

    private String email; // 수신자 이메일

    //  정적 팩토리 메서드 사용
    public static AddressBook create(String name, String email) {
        AddressBook addressBook = new AddressBook();
        addressBook.name = name;
        addressBook.email = email;
        return addressBook;
    }
}
