package com.example.movies.repository;

import com.example.movies.domain.Movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    public Movie findByTitle(String title);

    public Movie findByApiId(Long id);
}
