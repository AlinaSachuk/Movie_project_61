package com.tms.repository;

import com.tms.domain.Movie;
import com.tms.domain.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class UserRepository {
    public User getUserById(int id) {
        User user = new User();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_table WHERE id=?");
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
            user.setBirthdate(resultSet.getDate("birthday_date"));
            user.setDeleted(resultSet.getBoolean("is_deleted"));
            user.setTelephoneNumber(resultSet.getString("telephone"));
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return user;
    }

    public boolean createUser(User user) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_table (id, first_name, last_name, login, password, created, changed,email,birthday_date,is_deleted, telephone) " +
                    "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setDate(5, new Date((new java.util.Date()).getTime()));
            statement.setDate(6, new Date((new java.util.Date()).getTime()));
            statement.setString(7, user.getEmail());
            statement.setDate(8, new Date((new java.util.Date()).getTime()));
            statement.setString(9, user.getTelephoneNumber());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean updateUser(int id, String firstName, String lastName, String login, String password, String email, String telephoneNumber) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE user_table SET first_name=?, last_name=?, login=?, password=?, changed=?,email=?,birthday_date=?, telephone=? WHERE id=?");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setDate(5, new Date((new java.util.Date()).getTime()));
            statement.setString(6, email);
            statement.setDate(7, new Date((new java.util.Date()).getTime()));
            statement.setString(8, telephoneNumber);
            statement.setInt(9, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean addMovieToUser(int userId, int movieId) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO l_user_movie (id, user_id, movie_id)" +
                    "VALUES (DEFAULT, ?, ?)");
            statement.setInt(1, userId);
            statement.setInt(2, movieId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public ArrayList<Movie> getMoviesForSingleUser(int id) {
        ArrayList<Movie> movieList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT m.id, m.movie_name, m.year, m.genre, m.rating, m.description FROM l_user_movie JOIN movie_table as m ON l_user_movie.movie_id = m.id WHERE l_user_movie.user_id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setMovieName(resultSet.getString("movie_name"));
                movie.setYear(resultSet.getInt("year"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setRating(resultSet.getDouble("rating"));
                movie.setDescription(resultSet.getString("description"));
                movieList.add(movie);
            }
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return movieList;
    }

    public boolean deleteUser(int id) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE user_table SET is_deleted=true WHERE id=?");
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }
}
