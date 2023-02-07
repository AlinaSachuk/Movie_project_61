package com.tms.service;

import com.tms.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserCrudService {
    public User getUserById(int id) {
        User user = new User();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root");
            PreparedStatement statement = connection.prepareStatement("SELECT FROM user_table WHERE id=?");

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Something wrong...");
        }
        return user;
    }
    public boolean createUser(){

        return false;
    }
}
