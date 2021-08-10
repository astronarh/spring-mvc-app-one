package ru.astronarh.dao;

import org.springframework.stereotype.Component;
import ru.astronarh.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PersonDao {
    private List<Person> people = new ArrayList<>();

    {
        people.add(new Person(UUID.randomUUID().toString(), "One"));
        people.add(new Person(UUID.randomUUID().toString(), "Two"));
        people.add(new Person(UUID.randomUUID().toString(), "Tree"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(String personId) {
        return people.stream().filter(p ->p.getId().equals(personId)).findFirst().orElse(null);
    }
}
