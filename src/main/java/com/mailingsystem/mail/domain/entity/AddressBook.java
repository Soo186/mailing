package com.mailingsystem.mail.domain.entity;



import lombok.Getter;

@Getter
public class AddressBook {
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
    // 조회용 정적 팩토리

    public static AddressBook restore(Long id, String name, String email) {
        AddressBook addressBook = new AddressBook();
        addressBook.id = id;
        addressBook.name = name;
        addressBook.email = email;
        return addressBook;
    }
}
