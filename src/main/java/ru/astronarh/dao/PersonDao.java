package ru.astronarh.dao;

import org.springframework.stereotype.Component;
import ru.astronarh.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PersonDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getString("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public Person show(String personId) {
//        return people.stream().filter(p ->p.getId().equals(personId)).findFirst().orElse(null);
        return null;
    }

    public void add(Person person) {
//        people.add(person);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO person VALUES('" + UUID.randomUUID().toString() + "','" + person.getName() + "'," + person.getAge() + ",'" + person.getEmail() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(String personId) {
//        people.removeIf(p -> p.getId().equals(personId));
    }

    public void update(String personId, Person person) {
//        Person personToBeUpdated = show(personId);
//
//        personToBeUpdated.setName(person.getName());
//        personToBeUpdated.setAge(person.getAge());
//        personToBeUpdated.setEmail(person.getEmail());
    }
}
