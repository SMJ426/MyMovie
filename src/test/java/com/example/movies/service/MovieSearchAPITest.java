package com.example.movies.service;

import com.example.movies.dto.SearchRes;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MovieSearchAPITest {
    @Autowired
    MovieSearchAPI movieSearchAPI;
    @Test
    void searchMovie() {
    }

    @Test
    void searchMovieById() {
    }

    @Test
    void popularMovie() throws IOException, ParseException {
        List<SearchRes> popular=movieSearchAPI.popularMovie();
        System.out.println(popular.get(0).getTitle());
    }
}