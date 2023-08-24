package com.example.movies.controller;

import com.example.movies.dto.SearchRes;
import com.example.movies.service.MovieSearchAPI;
import com.example.movies.service.MovieService;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    private final MovieSearchAPI movieSearchAPI;
    private final MovieService movieService;

    public HomeController(MovieSearchAPI movieSearchAPI, MovieService movieService) {
        this.movieSearchAPI = movieSearchAPI;
        this.movieService = movieService;
    }
    @GetMapping("")
    public String index(Model model) throws IOException, ParseException {
        List<SearchRes> movies = movieSearchAPI.popularMovie();
        model.addAttribute("movies",movies);
        return "index";
    }
    @PostMapping("/genre")
    public String searchGenre(String genre,Model model) throws ParseException {
        System.out.println(genre);
        List<SearchRes> movies = movieSearchAPI.searchMovieByGenre(genre);
        model.addAttribute("movies",movies);
        return "index";

    }
}
