package com.example.ticketing.service;

import com.example.ticketing.model.Movie;
import com.example.ticketing.model.Repertoire;
import com.example.ticketing.repository.MovieRepository;
import com.example.ticketing.repository.RepertoireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

//   private final MovieRepository movieRepository;
//
//
//    public MovieService(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }
//
//
//    public List<Movie> ListAllMovies() {
//        return movieRepository.findAll();
//    }
//
//    public void saveMovie(Movie movie) {
//        movieRepository.save(movie);
//    }
//
//    public Optional<Movie> findByIdMovie(Long id) {
//        return movieRepository.findById(id);
//    }
//
//    public void deleteMovie(Long id) {
//        movieRepository.deleteById((id));
//    }
//
//    public void save(Movie movie) {
//        movieRepository.save(movie);
//    }
    @Autowired
     MovieRepository movieRepository;
    @Autowired
     RepertoireRepository repertoireRepository;

    public String getMovies(final Model model) {
        final List<Movie> movie = movieRepository.findAll();
        model.addAttribute("movie", movie);
        return "/movieIndex";
    }


    public String addMovie(final Movie movie, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            return "/add-movie";
        }
        movieRepository.save(movie);
        log.info("A new film has been added to the database" + movie.getName());
        return "redirect:/movies/list";
    }


    public String showUpdateFormMovie(final long id, final Model model) {
        final Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect ID: " + id));
        model.addAttribute("movie", movie);
        return "/update-movie";
    }


    public String updateMovie(final long id, final Movie movie) {
        final Movie movieFromDb = movieRepository.getOne(id);
        movieFromDb.setType(movie.getType());
        movieFromDb.setPlot(movie.getPlot());
        movieFromDb.setDuration(movie.getDuration());
        movieFromDb.setMinAge(movie.getMinAge());
        movieFromDb.setImageUrl(movie.getImageUrl());
        movieFromDb.setName(movie.getName());
        log.info("Movie data has been edited " + movie.getName());
        return "redirect:/movies/list";
    }


    public String deleteMovie(final long id, final Model model) {
        final List<Repertoire> repertoires = repertoireRepository.findByMovieId(id);
        repertoires.forEach(r -> repertoireRepository.deleteById(r.getId()));
        final Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect ID : " + id));
        movieRepository.delete(movie);
        final List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        log.info("Movie removed " + movie.getName());
        return "redirect:/movies/list";
    }

    public String showMovieRepertoireForm(final String movieName, final Model model) {
        final Movie movieRepertoire = movieRepository.findByName(movieName);
        model.addAttribute("movieRepertoire", movieRepertoire);
        model.addAttribute("repertoire", new Repertoire());
        return "/movie-repertoire";
    }

    public String addMovieRepertoire(final Repertoire repertoire, final Long movieId, final BindingResult result) {
        repertoire.setMovie(movieRepository.getOne(movieId));
        repertoireRepository.save(repertoire);
        log.info("A repertoire for the movie about ID " + movieId);
        return "redirect:/movies/list";
    }


    public String showUpdateMovieRepertoireForm(final String movieName, final Long repertoireId, final Model model) {
        final Repertoire repertoire = repertoireRepository.getOne(repertoireId);
        final Movie movieRepertoire = movieRepository.findByName(movieName);
        model.addAttribute("movieRepertoire", movieRepertoire);
        model.addAttribute("repertoire", repertoire);
        return "/movie-repertoire";
    }


    public String updateMovieRepertoire(final Repertoire repertoire, final Long repertoireId, final BindingResult result) {
        final Repertoire repertoireFromDb = repertoireRepository.getOne(repertoireId);
        repertoireFromDb.setDate(repertoire.getDate());
        log.info("Updated repertoire data for " + repertoire.getMovie().getName());
        return "redirect:/movies/list";
    }


    public String deleteMovieRepertoire(final Long repertoireId, final Model model) {
        repertoireRepository.deleteById(repertoireId);
        log.info("Repertoire with ID removed " + repertoireId);
        return "/movieIndex";
    }
}
