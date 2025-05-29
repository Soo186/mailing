package com.mailingsystem.mail.api.controller;

import com.mailingsystem.mail.api.dto.AddressBookRequest;
import com.mailingsystem.mail.api.dto.AddressBookResponse;
import com.mailingsystem.mail.application.service.AddressBookCommandService;
import com.mailingsystem.mail.application.service.AddressBookQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/address-book")
@RequiredArgsConstructor
public class AddressBookController {

    private final AddressBookCommandService addressBookCommandService;
    private final AddressBookQueryService addressBookQueryService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody AddressBookRequest request) {
        addressBookCommandService.register(request.getName(), request.getEmail());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AddressBookResponse>> getAll() {
        List<AddressBookResponse> result = addressBookQueryService.getAllAddresses().stream()
                .map(AddressBookResponse::new)
                .toList();

        return ResponseEntity.ok(result);
    }

}
