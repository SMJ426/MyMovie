package com.example.movies.controller;

import com.example.movies.domain.Movie.Movie;
import com.example.movies.domain.User.Principal;
import com.example.movies.domain.User.User;
import com.example.movies.dto.Cast;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.example.movies.dto.SearchReq;
import com.example.movies.dto.SearchRes;

import com.example.movies.service.MovieSearchAPI;
import com.example.movies.service.MovieService;
import com.example.movies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieSearchAPI movieSearchAPI;
    private final MovieService movieService;
    private final UserService userService;
    @PostMapping("/search")
    public String search(SearchReq searchReq, Model model) throws IOException, ParseException{
        List<SearchRes> movies = movieSearchAPI.searchMovie(searchReq);
        model.addAttribute("size",movies.size());
        model.addAttribute("movies",movies);
        return "movies";
    }
    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable String id) throws IOException, ParseException {
        SearchRes movie = movieSearchAPI.searchMovieById(id);
        List<SearchRes> movies = movieSearchAPI.popularMovie();
        List<Cast> casts = movieSearchAPI.searchCastById(id);
        model.addAttribute("movies",movies);
        model.addAttribute("movie",movie);
        model.addAttribute("casts",casts);

        return "movieDetail";
    }
    @GetMapping("/credit/{cast_id}")
    public String castDetail(Model model, @PathVariable String cast_id) throws IOException, ParseException {
        Cast cast = movieSearchAPI.searchCast(cast_id);
        List<SearchRes> movies = movieSearchAPI.searchCastDetail(cast_id);
        model.addAttribute("cast",cast);
        model.addAttribute("movies",movies);
        return "actorDetail";
    }
    @PostMapping("/{id}/like")
    public String addMyMovie(@PathVariable String id,Model model) throws IOException, ParseException {
        SearchRes myMovie = movieSearchAPI.searchMovieById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            Principal user = (Principal) authentication.getPrincipal();
            String msg=movieService.addMovies(myMovie,user.getUsername());
            List<SearchRes> myMovieList = movieService.showMovies();
            model.addAttribute("movies",myMovieList);
            model.addAttribute("msg",msg);
            return "myMovie";
        }catch (Exception e){

            return "loginPage";
        }


    }
}
