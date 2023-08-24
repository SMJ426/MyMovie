package com.example.movies.controller;

import com.example.movies.domain.Movie.Movie;
import com.example.movies.dto.SearchRes;
import com.example.movies.dto.UserDto;
import com.example.movies.service.MovieService;
import com.example.movies.service.UserService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final MovieService movieService;

    public UserController(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }
    @GetMapping("/join")
    public String join(){return "signup";}
    @GetMapping("/login")
    public String login(){return "loginPage";}
    @GetMapping("/home")
    public String home(Model model){return "/home";}
    @PostMapping("/join")
    public String newJoin(@Valid UserDto userDto){
        if (userService.signup(userDto)){return "loginPage";}
        else{return "findPW";}
    }
    @GetMapping("/find")
    public String findUser(){return "findPW";}

    @GetMapping("/like")
    public String myMovie(Model model){
        try{
            List<SearchRes> myMovieList = userService.getMyMovie();
            model.addAttribute("movies",myMovieList);
            return "myMovie";
        }catch(Exception e){
            return "loginPage";
        }
        }
}
