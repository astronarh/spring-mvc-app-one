package ru.astronarh.dao;

import org.springframework.stereotype.Component;
import ru.astronarh.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PersonDao {
    private static List<Person> people = new ArrayList<>();

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

    public void add(Person person) {
        people.add(person);
    }

    public void delete(String personId) {
        people.removeIf(p -> p.getId().equals(personId));
    }

    public void update(String personId, Person person) {
        Person personToBeUpdated = show(personId);

        personToBeUpdated.setName(person.getName());
    }
}
