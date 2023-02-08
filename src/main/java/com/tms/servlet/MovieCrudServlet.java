package com.tms.servlet;

import com.tms.domain.Movie;
import com.tms.service.MovieCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/movie")
public class MovieCrudServlet extends HttpServlet {
    MovieCrudService movieCrudService = new MovieCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int requestMovieId = Integer.parseInt(req.getParameter("id"));
        Movie movie = movieCrudService.getMovieById(requestMovieId);
        req.setAttribute("movie", movie);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/singleMovie.jsp").forward(req, resp);
    }
}
