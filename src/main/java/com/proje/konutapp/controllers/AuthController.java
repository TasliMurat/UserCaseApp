package com.proje.konutapp.controllers;

import com.proje.konutapp.config.JwtProvider;
import com.proje.konutapp.entities.User;
import com.proje.konutapp.repos.UserRepository;
import com.proje.konutapp.requests.LoginRequest;
import com.proje.konutapp.responses.AuthResponse;
import com.proje.konutapp.services.AuthService;
import com.proje.konutapp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{
        return authService.createUser(user);
    }

    @PostMapping("/login")
    public AuthResponse signInHandler(@RequestBody LoginRequest loginRequest){
        return authService.signInHandler(loginRequest);
    }
}
