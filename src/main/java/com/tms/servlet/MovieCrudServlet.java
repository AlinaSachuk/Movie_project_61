package com.tms.servlet;

import com.tms.domain.Movie;
import com.tms.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/movie")
public class MovieCrudServlet extends HttpServlet {
    MovieService movieService = new MovieService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestMovieId = Integer.parseInt(req.getParameter("id"));
        Movie movie = movieService.getMovieById(requestMovieId);
        req.setAttribute("movie", movie);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/singleMovie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName = req.getParameter("movieName");
        int year = Integer.parseInt(req.getParameter("year"));
        String genre = req.getParameter("genre");
        double rating = Double.parseDouble(req.getParameter("rating"));
        String description = req.getParameter("description");
        boolean result = movieService.createMovie(movieName, year, genre, rating, description);
        if (result) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/successfully.jsp").forward(req, resp);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/unsuccessfully.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String movieName = req.getParameter("movieName");
        int year = Integer.parseInt(req.getParameter("year"));
        String genre = req.getParameter("genre");
        double rating = Double.parseDouble(req.getParameter("rating"));
        String description = req.getParameter("description");
        movieService.updateMovie(id, movieName, year, genre, rating, description);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        movieService.deleteMovie(id);
    }
}
