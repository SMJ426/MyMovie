package com.example.movies.service;

import com.example.movies.domain.Movie.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MovieServiceTest {
    @Autowired
    MovieService movieService;
    @Test
    void addMovies() {
        Movie myMovies = Movie.builder()
                .api_id(10138L)
                .build();

        System.out.println(movieService.duplicated(myMovies));
    }

    @Test
    void duplicated() {

    }

    @Test
    void showMovies() {
    }

    @Test
    void findMovieByTitle() {
    }
}