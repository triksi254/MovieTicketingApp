package com.example.ticketing.repository;

import com.example.ticketing.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MovieRepository
        extends JpaRepository<Movie, Long> {
    Movie findByName(String movieName);
    Movie getById(Long movieId);
}

