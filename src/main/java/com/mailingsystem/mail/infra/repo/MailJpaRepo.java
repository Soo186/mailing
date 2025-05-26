package com.mailingsystem.mail.infra.repo;

import com.mailingsystem.mail.domain.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailJpaRepo extends JpaRepository<Mail, Long> {

}
