package com.tms.service;

import com.tms.domain.Movie;

import java.sql.*;

public class MovieCrudService {
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Movie getMovieById(int id) {
        Movie movie = new Movie();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM movie_table WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            movie.setId(resultSet.getInt("id"));
            movie.setMovieName(resultSet.getString("movie_name"));
            movie.setYear(resultSet.getInt("year"));
            movie.setGenre(resultSet.getString("genre"));
            movie.setRating(resultSet.getDouble("rating"));
            movie.setDescription(resultSet.getString("description"));
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return movie;
    }
}
