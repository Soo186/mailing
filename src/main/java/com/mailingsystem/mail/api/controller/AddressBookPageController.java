package com.mailingsystem.mail.api.controller;


import com.mailingsystem.mail.application.service.AddressBookQueryService;
import com.mailingsystem.mail.domain.entity.AddressBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address-book")
public class AddressBookPageController {

    private final AddressBookQueryService addressBookService;

    @GetMapping("/list")
    public String showListPage(Model model) {
        List<AddressBook> entries = addressBookService.getAllAddresses();
        model.addAttribute("entries", entries);
        return "address-book-list"; // /templates/address-book-list.html
    }
}
