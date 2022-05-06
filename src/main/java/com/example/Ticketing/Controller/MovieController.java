package com.example.ticketing.controller;
import com.example.ticketing.model.Movie;
import com.example.ticketing.model.Repertoire;
import com.example.ticketing.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class MovieController {
//    private final  MovieService movieService;
//
//    public MovieController(MovieService movieService) {
//        this.movieService = movieService;
//    }
//
//    @PostMapping("")
//    public ResponseEntity<Movie> newMovie(@RequestBody Movie movie) {
//        movieService.save(movie);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("")
//    public List<Movie> list(){
//        return movieService.ListAllMovies();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> get(@PathVariable Long id) {
//        try {
//            Optional<Movie> movie = movieService.findByIdMovie(id);
//            return new ResponseEntity<Object>(movie, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@RequestBody @NotNull Movie movie, @PathVariable Long id) {
//        try {
//            Optional<Movie> existMovie = movieService.findByIdMovie(id);
//            movie.setId(id);
//            movieService.saveMovie(movie);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//
//        movieService.deleteMovie(id);
//    }

    static final class Routes {
        static final String ROOT = "/movies";
        static final String USER = ROOT + "/user";
        static final String MOVIE_NAME = USER + "/{movieName}";
        static final String LIST = ROOT + "/list";
        static final String FORM = ROOT + "/showForm";
        static final String ADD = ROOT + "/add";
        static final String EDIT = ROOT + "/edit/{id}";
        static final String UPDATE = ROOT + "/update/{id}";
        static final String DELETE = ROOT + "/delete/{id}";
        static final String ADD_REPERTOIRE = USER + "/newRepertoire";
        static final String NEW_REPERTOIRE = MOVIE_NAME + "/newRepertoire";
        static final String UPDATE_REPERTOIRE_ID = MOVIE_NAME + "/updateRepertoire/{repertoireId}";
        static final String UPDATE_REPERTOIRE = USER + "/updateRepertoire";
        static final String DELETE_REPERTOIRE = USER + "/deleteRepertoire/{repertoireId}";
    }
    @Autowired
    MovieService movieService;

    @GetMapping(Routes.LIST)
    public String getMovies(final Model model) {
        return movieService.getMovies(model);
    }

    @GetMapping(Routes.FORM)
    public String showMovieForm(final Movie movie) {
        return "/add-movie";
    }

    @PostMapping(Routes.ADD)
    public String addMovie(@Validated final Movie movie, final BindingResult result, final Model model) {
        return movieService.addMovie(movie, result, model);
    }

    @GetMapping(Routes.EDIT)
    public String showUpdateFormMovie(@PathVariable("id") final long id, final Model model) {
        return movieService.showUpdateFormMovie(id, model);
    }

    @PostMapping(Routes.UPDATE)
    @Transactional
    public String updateMovie(@PathVariable("id") final long id, @Validated final Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @GetMapping(Routes.DELETE)
    public String deleteMovie(@PathVariable("id") final long id, final Model model) {
        return movieService.deleteMovie(id, model);
    }

    @GetMapping(Routes.NEW_REPERTOIRE)
    public String showMovieRepertoireForm(@PathVariable("movieName") final String movieName, final Model model) {
        return movieService.showMovieRepertoireForm(movieName, model);
    }

    @PostMapping(Routes.ADD_REPERTOIRE)
    @Transactional
    public String addMovieRepertoire(@ModelAttribute("repertoire") final Repertoire repertoire,
                                     @ModelAttribute("movieId") final Long movieId, final BindingResult result) {
        return movieService.addMovieRepertoire(repertoire, movieId, result);
    }

    @GetMapping(Routes.UPDATE_REPERTOIRE_ID)
    public String showUpdateMovieRepertoireForm(@PathVariable("movieName") final String movieName,
                                                @PathVariable("repertoireId") final Long repertoireId, final Model model) {
        return movieService.showUpdateMovieRepertoireForm(movieName, repertoireId, model);
    }

    @PostMapping(Routes.UPDATE_REPERTOIRE)
    @Transactional
    public String updateMovieRepertoire(@ModelAttribute("repertoire") final Repertoire repertoire,
                                        @ModelAttribute("movieId") final Long movieId,
                                        @ModelAttribute("repertoireId") final Long repertoireId, final BindingResult result) {
        return movieService.updateMovieRepertoire(repertoire, repertoireId, result);
    }

    @GetMapping(Routes.DELETE_REPERTOIRE)
    @Transactional
    public String deleteMovieRepertoire(@PathVariable("repertoireId") final Long repertoireId, final Model model) {
        return movieService.deleteMovieRepertoire(repertoireId, model);
    }
}
