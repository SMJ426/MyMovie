package com.example.movies.service;


import com.example.movies.domain.Movie.Movie;
import com.example.movies.dto.SearchRes;
import com.example.movies.repository.MovieRepository;
import com.example.movies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public String addMovies(SearchRes searchRes, String email){
        Movie myMovies = Movie.builder()
                .api_id(searchRes.getId())
                .title(searchRes.getTitle())
                .poster_path(searchRes.getPoster_path())
                .user(userRepository.findByEmail(email).orElseThrow())
                .build();
        if(duplicated(myMovies)){
            return "이미 등록한 영화입니다";
        }
        else{
            movieRepository.save(myMovies);
            return "성공적으로 처리되었습니다";
        }

    }
    public boolean duplicated(Movie movie){
        return Objects.equals(movie, movieRepository.findByApiId(movie.getApiId()));
    }
    public List<SearchRes> showMovies(){

        List<SearchRes> myMovies = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> {SearchRes searchRes = SearchRes.builder()
                .id(movie.getApiId())
                .title(movie.getTitle())
                .poster_path(movie.getPoster_path())
                .build();
            myMovies.add(searchRes);});
        return myMovies;

    }
    public Movie findMovieByTitle(String title){
        return movieRepository.findByTitle(title);
    }

}
