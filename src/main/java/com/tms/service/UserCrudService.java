package com.tms.service;

import com.tms.domain.User;

import java.sql.*;

public class UserCrudService {

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        User user = new User();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root");
            PreparedStatement statement = connection.prepareStatement("SELECT FROM user_table WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setCreated(resultSet.getDate("created"));
            user.setChanged(resultSet.getDate("changed"));
            user.setEmail(resultSet.getString("email"));
            user.setTelephoneNumber(resultSet.getString("telephone"));
            user.setBirthdate(resultSet.getDate("birthday_date"));
            user.setDeleted(resultSet.getBoolean("is_deleted"));

            connection.close();
        } catch (SQLException e) {
            System.out.println("something wrong...");
        }
        return user;
    }

    public boolean createUser() {

        return false;
    }
}
