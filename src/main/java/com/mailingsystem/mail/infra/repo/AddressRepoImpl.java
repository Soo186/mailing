package com.mailingsystem.mail.infra.repo;

import com.mailingsystem.mail.domain.entity.AddressBook;
import com.mailingsystem.mail.domain.repo.AddressBookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepoImpl implements AddressBookRepo {

    private final AddressJpaRepo addressJpaRepo;


    @Override
    public AddressBook save(AddressBook addressBook) {
        return addressJpaRepo.save(addressBook);
    }

    @Override
    public Optional<AddressBook> findById(Long id) {
        return addressJpaRepo.findById(id);
    }

    @Override
    public List<AddressBook> findAll() {
        return addressJpaRepo.findAll();
    }

}
