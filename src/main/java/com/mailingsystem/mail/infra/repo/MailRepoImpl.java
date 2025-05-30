package com.mailingsystem.mail.infra.repo;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.enums.MailStatus;
import com.mailingsystem.mail.domain.repo.MailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MailRepoImpl implements MailRepo {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = "INSERT INTO mail (to_address, subject, body, status) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM mail WHERE id = ?";

    private static final RowMapper<Mail> rowMapper = (rs, rowNum) -> Mail.restore(
            rs.getLong("id"),
            rs.getString("to_address"),
            rs.getString("subject"),
            rs.getString("body"),
            MailStatus.valueOf(rs.getString("status"))
    );

    @Override
    public Mail save(Mail mail) {
        jdbcTemplate.update(
                INSERT_SQL,
                mail.getToAddress(),
                mail.getSubject(),
                mail.getBody(),
                mail.getStatus().name()
        );
        return mail;
    }

    @Override
    public Optional<Mail> findById(Long id) {
        return jdbcTemplate.query(SELECT_BY_ID, rowMapper, id).stream().findFirst();
    }
}
