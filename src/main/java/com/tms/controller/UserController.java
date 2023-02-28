package com.tms.controller;

import com.tms.Main;
import com.tms.domain.Movie;
import com.tms.domain.User;
import com.tms.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;
    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        System.out.println(10/2);
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "singleUser";
    }

    @GetMapping("/getMovies/{id}")
    public String giveAllMoviesForThisUser (@PathVariable int id, Model model){
        ArrayList<Movie> movieList = userService.getMoviesForSingleUser(id);
        model.addAttribute("movieList", movieList.toString());
        return "allMovies";
    }

    @PostMapping
    public String createUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            for (ObjectError o : bindingResult.getAllErrors()){
                log.warn("We have bindingResult error: " + o);
            }
            return "unsuccessfully";
        }
        boolean result = userService.createUser(user);
        if (result) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @PostMapping
    public String addFilm(@RequestParam int userId, @RequestParam int movieId){
        if (userService.addMovieToUser(userId, movieId)) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @PutMapping
    public String updateUser(@RequestParam String id,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String login,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam String telephoneNumber
    ) {
        boolean result = userService.updateUser(Integer.parseInt(id), firstName, lastName, login, password, email, telephoneNumber);
        if (result) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @DeleteMapping("{/id}")
    public String deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id)) {
            return "successfully";
        }
        return "unsuccessfully";
    }
    /* @ExceptionHandler(ArithmeticException.class)
    public String myFirstExHand(Exception e){
        log.warn("Arithmetic exception: " + e);
        return "unsuccessfully";
    }*/
}
