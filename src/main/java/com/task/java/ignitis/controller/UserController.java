package com.task.java.ignitis.controller;

import com.task.java.ignitis.payload.response.InfoResponse;
import com.task.java.ignitis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{username}")
    public InfoResponse deleteUser(@PathVariable("username") String username) {
        return userService.deleteUser(username);
    }
}
