package ru.astronarh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.astronarh.dao.PersonDao;
import ru.astronarh.model.Person;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDao personDao;

    public List<Person> index() {
        return personDao.index();
    }

    public Person show(String personId) {
        return personDao.show(personId);
    }

    public void add(Person person) {
        personDao.add(person);
    }

    public void delete(String personId) {
        personDao.delete(personId);
    }

    public void update(String personId, Person person) {
        personDao.update(personId, person);
    }
}
