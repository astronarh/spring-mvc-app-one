package ru.astronarh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.astronarh.config.DbConnection;
import ru.astronarh.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PersonDao {
    private static final String SELECT_ALL = "SELECT * FROM person";
    private static final String SELECT_BY_ID = "SELECT * FROM person WHERE id=?";
    private static final String INSERT_INTO = "INSERT INTO person VALUES(?,?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM person WHERE id=?";
    private static final String UPDATE_BY_ID = "UPDATE person SET name=?, age=?, email=? WHERE id=?";

    @Autowired
    private DbConnection dbConnection;

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();
        Statement statement = dbConnection.get().createStatement();

        ResultSet resultSet = statement.executeQuery(SELECT_ALL);

        while (resultSet.next()) {
            Person person = new Person();

            person.setId(resultSet.getString("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));

            people.add(person);
        }
        return people;
    }

    public Person show(String personId) throws SQLException {
        Person person = null;

        PreparedStatement preparedStatement = dbConnection.get().prepareStatement(SELECT_BY_ID);

        preparedStatement.setString(1, personId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            person = new Person();

            person.setId(resultSet.getString("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));

            return person;
        }

        return person;
    }

    public void add(Person person) throws SQLException {
        PreparedStatement preparedStatement = dbConnection.get().prepareStatement(INSERT_INTO);

        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setInt(3, person.getAge());
        preparedStatement.setString(4, person.getEmail());

        preparedStatement.executeUpdate();
    }

    public void delete(String personId) throws SQLException {
        PreparedStatement preparedStatement = dbConnection.get().prepareStatement(DELETE_BY_ID);

        preparedStatement.setString(1, personId);
        preparedStatement.executeUpdate();
    }

    public void update(String personId, Person person) throws SQLException {
        PreparedStatement preparedStatement = dbConnection.get().prepareStatement(UPDATE_BY_ID);

        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.setString(4, personId);

        preparedStatement.executeUpdate();
    }
}
