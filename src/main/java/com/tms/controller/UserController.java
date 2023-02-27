package com.tms.controller;

import com.tms.domain.User;
import com.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "singleUser";
    }

    @PostMapping
    public String createUser(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String login,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam String telephoneNumber
    ) {
        boolean result = userService.createUser(firstName, lastName, login, password, email, telephoneNumber);
        if (result) {
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
}
