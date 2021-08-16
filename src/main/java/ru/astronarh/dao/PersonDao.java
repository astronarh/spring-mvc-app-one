package ru.astronarh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.astronarh.model.Person;

import java.util.List;
import java.util.UUID;

@Component
public class PersonDao {
    private static final String SELECT_ALL = "SELECT * FROM person";
    private static final String SELECT_BY_ID = "SELECT * FROM person WHERE id=?";
    private static final String INSERT_INTO = "INSERT INTO person VALUES(?,?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM person WHERE id=?";
    private static final String UPDATE_BY_ID = "UPDATE person SET name=?, age=?, email=? WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper<Person> PERSON_MAPPER = new BeanPropertyRowMapper<>(Person.class);

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query(SELECT_ALL, PERSON_MAPPER);
    }

    public Person show(String personId) {
        return jdbcTemplate.query(SELECT_BY_ID, new Object[]{personId}, PERSON_MAPPER).stream().findAny().orElse(null);
    }

    public void add(Person person) {
        jdbcTemplate.update(INSERT_INTO, UUID.randomUUID().toString(), person.getName(), person.getAge(), person.getEmail());
    }

    public void update(String personId, Person person) {
        jdbcTemplate.update(UPDATE_BY_ID, person.getName(), person.getAge(), person.getEmail(), personId);
    }

    public void delete(String personId) {
        jdbcTemplate.update(DELETE_BY_ID, personId);
    }
}
