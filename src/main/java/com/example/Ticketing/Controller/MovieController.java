package com.example.Ticketing.Controller;

import com.example.Ticketing.Model.Movie;
import com.example.Ticketing.Service.MovieService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping(path ="api/v1/movie")
public class MovieController {
    private final  MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("")
    public ResponseEntity<Movie> newMovie(@RequestBody Movie movie) {
        movieService.save(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("")
    public List<Movie> list(){
        return movieService.ListAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        try {
            Optional<Movie> movie = movieService.findByIdMovie(id);
            return new ResponseEntity<Object>(movie, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @NotNull Movie movie, @PathVariable Long id) {
        try {
            Optional<Movie> existMovie = movieService.findByIdMovie(id);
            movie.setId(id);
            movieService.saveMovie(movie);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        movieService.deleteMovie(id);
    }
}
