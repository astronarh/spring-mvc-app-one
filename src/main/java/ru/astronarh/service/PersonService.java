package ru.astronarh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.astronarh.dao.PersonDao;
import ru.astronarh.model.Person;

import java.sql.SQLException;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDao personDao;

    public List<Person> index() throws SQLException {
        return personDao.index();
    }

    public Person show(String personId) throws SQLException {
        return personDao.show(personId);
    }

    public void add(Person person) throws SQLException {
        personDao.add(person);
    }

    public void delete(String personId) throws SQLException {
        personDao.delete(personId);
    }

    public void update(String personId, Person person) throws SQLException {
        personDao.update(personId, person);
    }
}
