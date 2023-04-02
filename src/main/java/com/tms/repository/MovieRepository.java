package com.tms.repository;

import com.tms.domain.Movie;
import com.tms.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;

@Repository
public class MovieRepository {
    private final SessionFactory sessionFactory;

    public MovieRepository() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public Movie getMovieById(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Movie m where m.id=:movieId");
        query.setParameter("movieId", id);
        Movie movie = (Movie) query.getSingleResult();
        session.close();
        if (movie != null) {
            return movie;
        }
        return new Movie();
    }

    public boolean createMovie(String movieName, int year, String genre, double rating, String description) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO movie_table (id, movie_name, year, genre, rating, description) " +
                    "VALUES (DEFAULT, ?, ?, ?, ?, ?)");
            statement.setString(1, movieName);
            statement.setInt(2, year);
            statement.setString(3, genre);
            statement.setDouble(4, rating);
            statement.setString(5, description);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean updateMovie(int id, String movieName, int year, String genre, double rating, String description) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE movie_table SET movie_name=?, year=?, genre=?, rating=?, description=? WHERE id=?");
            statement.setString(1, movieName);
            statement.setInt(2, year);
            statement.setString(3, genre);
            statement.setDouble(4, rating);
            statement.setString(5, description);
            statement.setInt(6, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean deleteMovie(int id) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM movie_table WHERE id=?");
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }
}
