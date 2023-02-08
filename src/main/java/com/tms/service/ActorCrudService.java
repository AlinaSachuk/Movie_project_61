package com.tms.service;

import com.tms.domain.Actor;

import java.sql.*;

public class ActorCrudService {
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Actor getActorById(int id) {
        Actor actor = new Actor();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM actors WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            actor.setId(resultSet.getInt("id"));
            actor.setFirstName(resultSet.getString("first_name"));
            actor.setLastName(resultSet.getString("last_name"));
            actor.setAge(resultSet.getInt("age"));
            actor.setBiography(resultSet.getString("biography"));
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return actor;
    }
}
