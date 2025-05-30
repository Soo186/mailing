package com.mailingsystem.mail.infra.repo;

import com.mailingsystem.mail.domain.entity.AddressBook;
import com.mailingsystem.mail.domain.repo.AddressBookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepoImpl implements AddressBookRepo {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = "INSERT INTO address_book (name, email) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM address_book";
    private static final String SELECT_BY_ID = "SELECT * FROM address_book WHERE id = ?";

    private static final RowMapper<AddressBook> rowMapper = (rs, rowNum) ->
            AddressBook.restore(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );

    @Override
    public AddressBook save(AddressBook addressBook) {
        jdbcTemplate.update(INSERT_SQL, addressBook.getName(), addressBook.getEmail());

        // 가장 최근 insert된 id를 다시 조회해서 반환 (id 필드가 AUTO_INCREMENT 라고 가정)
        Long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return AddressBook.restore(
                newId,
                addressBook.getName(),
                addressBook.getEmail()
        );
    }

    @Override
    public Optional<AddressBook> findById(Long id) {
        return jdbcTemplate.query(SELECT_BY_ID, rowMapper, id).stream().findFirst();
    }

    @Override
    public List<AddressBook> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }
}
