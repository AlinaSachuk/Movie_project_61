package com.tms.controller;

import com.tms.domain.Movie;
import com.tms.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movie")
public class MovieController {
    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public String getMovie(@PathVariable int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "singleMovie";
    }

    @PostMapping
    public String createMovie(@RequestParam String movieName,
                              @RequestParam int year,
                              @RequestParam String genre,
                              @RequestParam double rating,
                              @RequestParam String description
    ) {
        boolean result = movieService.createMovie(movieName, year, genre, rating, description);
        return result ? "successfully" : "unsuccessfully";
    }

    @PutMapping
    public String updateMovie(@RequestParam int id,
                              @RequestParam String movieName,
                              @RequestParam int year,
                              @RequestParam String genre,
                              @RequestParam double rating,
                              @RequestParam String description
    ) {
        boolean result = movieService.updateMovie(id, movieName, year, genre, rating, description);
        return result ? "successfully" : "unsuccessfully";
    }

    @DeleteMapping("/{id}")
    public String deleteMovie (@PathVariable int id){
        boolean result = movieService.deleteMovie(id);
        return result ? "successfully" : "unsuccessfully";
    }
}
