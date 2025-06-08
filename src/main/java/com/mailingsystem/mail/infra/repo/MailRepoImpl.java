package com.mailingsystem.mail.infra.repo;

import com.mailingsystem.mail.domain.entity.Mail;
import com.mailingsystem.mail.domain.enums.MailStatus;
import com.mailingsystem.mail.domain.repo.MailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;
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
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    INSERT_SQL,
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, mail.getToAddress());
            ps.setString(2, mail.getSubject());
            ps.setString(3, mail.getBody());
            ps.setString(4, mail.getStatus().name());
            return ps;
        }, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();

        //새 객체 반환
        return Mail.restore(
                generatedId,
                mail.getToAddress(),
                mail.getSubject(),
                mail.getBody(),
                mail.getStatus()
        );
    }
    @Override
    public Optional<Mail> findById(Long id) {
        return jdbcTemplate.query(SELECT_BY_ID, rowMapper, id).stream().findFirst();
    }
}
