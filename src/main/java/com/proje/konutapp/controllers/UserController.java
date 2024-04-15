package com.proje.konutapp.controllers;

import com.proje.konutapp.entities.User;
import com.proje.konutapp.repos.UserRepository;
import com.proje.konutapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/users/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwt(jwt);

        return user;
    }
}
