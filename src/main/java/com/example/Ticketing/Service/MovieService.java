package com.example.Ticketing.Service;

import com.example.Ticketing.Model.Movie;
import com.example.Ticketing.Repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

   private final MovieRepository movieRepository;


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public List<Movie> ListAllMovies() {
        return movieRepository.findAll();
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Optional<Movie> findByIdMovie(Long id) {
        return movieRepository.findById(id);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById((id));
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }
}
