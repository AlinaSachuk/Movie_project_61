package com.tms.controller;

import com.tms.domain.User;
import com.tms.domain.dto.UserDto;
import com.tms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Tag(name = "testTag")
    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUser() {
        ArrayList<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "This method will give you user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All is going great"),
            @ApiResponse(responseCode = "400", description = "Failed to get user by id..."),
            @ApiResponse(responseCode = "500", description = "Server error...")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@Parameter(description = "this is id you need to enter to get user") @PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@GetMapping("/getMovies/{id}")
    //public ResponseEntity<ArrayList<Movie>> giveAllMoviesForThisUser(@PathVariable int id) {
    //    return new ResponseEntity<>(userService.getMoviesForSingleUser(id), HttpStatus.OK);
    //}

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
            }
        }
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@PostMapping("/addFilm")
    //public ResponseEntity<HttpStatus> addFilm(@RequestParam int userId, @RequestParam int movieId) {
    //    userService.addMovieToUser(userId, movieId);
    //    return new ResponseEntity<>(HttpStatus.CREATED);
    //}

    @PutMapping
    public void updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/ln/{lastName}")
    public ResponseEntity<User> findUserByLastName(@PathVariable String lastName) {
        Optional<User> user = userService.findUserByLastName(lastName);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }
    /* @ExceptionHandler(ArithmeticException.class)
    public String myFirstExHand(Exception e){
        log.warn("Arithmetic exception: " + e);
        return "unsuccessfully";
    }*/
}
