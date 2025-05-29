package com.mailingsystem.mail.infra.repo;

import com.mailingsystem.mail.domain.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressJpaRepo extends JpaRepository<AddressBook, Long> {

}
