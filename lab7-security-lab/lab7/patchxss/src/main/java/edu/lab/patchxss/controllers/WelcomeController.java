package edu.lab.patchxss.controllers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.lab.patchxss.models.User;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @PostMapping
    public User postUser(@RequestBody User user) {
        user.setUser("Hey there! Welcome to the localhost, " + user.getUser());
        return user;
    }
}