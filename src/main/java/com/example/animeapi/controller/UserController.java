package com.example.animeapi.controller;

import com.example.animeapi.domain.User;
import com.example.animeapi.repository.AnimeRepository;
import com.example.animeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> findAllUsers(){ return userRepository.findAll(); }

}
