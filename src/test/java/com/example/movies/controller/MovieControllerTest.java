package com.example.movies.controller;

import com.example.movies.domain.Movie.Movie;
import com.example.movies.domain.User.User;
import com.example.movies.dto.SearchRes;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.MovieService;
import com.example.movies.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MovieControllerTest {
    @Autowired
    MovieService movieService;
    @Autowired
    UserService userService;
    @Autowired
    MovieRepository movieRepository;
    @Test
    void search() {
    }

    @Test
    void detail() {
    }

    @Test
    void addMyMovie() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());

    }
}