package com.example.movies.service;



import com.example.movies.aop.TimeCheck;

import com.example.movies.domain.Movie.Movie;
import com.example.movies.domain.User.Principal;
import com.example.movies.domain.User.User;

import com.example.movies.dto.SearchRes;
import com.example.movies.dto.UserDto;

import com.example.movies.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService{
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public boolean duplicated(UserDto userDto){
        return userRepository.findByEmail(userDto.getEmail()).isPresent();
    }
    @TimeCheck
    public boolean signup(UserDto userDto){
        if (duplicated(userDto)){return false;}
        User user = User.builder()
                .birth(userDto.getBirth())
                .role(userDto.getRole())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .address(userDto.getAddress())
                .phoneNum(userDto.getPhoneNum())
                .userName(userDto.getUserName()).build();
        userRepository.save(user);
        return true;
    }
    public User findUser(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }
    public List<SearchRes> getMyMovie(){
        List<SearchRes> movieList = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Principal user = (Principal) auth.getPrincipal();
        User me=findUser(user.getUsername());
        List<Movie> myMovie = (List<Movie>)me.getMyMovies();
        myMovie.forEach(m->{movieList.add(SearchRes.builder()
                        .id(m.getApiId())
                        .title(m.getTitle())
                        .poster_path(m.getPoster_path())
                .build());});
        return movieList;
    }
}
