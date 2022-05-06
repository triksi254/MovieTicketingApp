package com.example.ticketing.controller;

import com.example.ticketing.model.ReserveSeatConfiguration;
import com.example.ticketing.service.ReservationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@Controller
@NoArgsConstructor
@RequestMapping
public class ReservationController {
    static final class Routes {
        static final String MOVIE_ROOT = "/movies/{movieName}";
        static final String SPECTACLE_ROOT = "/spectacles/{spectacleName}";
        static final String RESERVATION_ROOT = "/reservation";
        static final String REPERTOIRE_ROOT = "/{repertoireId}";
        static final String MOVIE_RESERVATION = MOVIE_ROOT + RESERVATION_ROOT;
        static final String SPECTACLE_RESERVATION = SPECTACLE_ROOT + RESERVATION_ROOT;
        static final String MOVIE_RESERVATION_ID = MOVIE_ROOT + RESERVATION_ROOT + REPERTOIRE_ROOT;
        static final String SPECTACLE_RESERVATION_ID = SPECTACLE_ROOT + RESERVATION_ROOT + REPERTOIRE_ROOT;
        static final String SEAT_RESERVATION = RESERVATION_ROOT + "/save/{repertoireId}";
    }
    @Autowired
    ReservationService reservationService;

    @GetMapping(Routes.MOVIE_RESERVATION)
    public String movieReservationPage(@PathVariable("movieName") final String movieName, final Model model) {
        return reservationService.showMovieReservationPage(movieName, model);
    }

    @GetMapping(Routes.SPECTACLE_RESERVATION)
    public String spectacleReservationPage(@PathVariable("spectacleName") final String spectacleName, final Model model) {
        return reservationService.showSpectacleReservationPage(spectacleName, model);
    }

    @GetMapping(Routes.MOVIE_RESERVATION_ID)
    public String movieReservationSeatPage(@PathVariable("movieName") final String movieName,
                                           @PathVariable("repertoireId") final Long repertoireId, final Model model) {
        return reservationService.showMovieReservationSeatPage(movieName, repertoireId, model);
    }

    @GetMapping(Routes.SPECTACLE_RESERVATION_ID)
    public String spectacleReservationSeatPage(@PathVariable("spectacleName") final String spectacleName,
                                               @PathVariable("repertoireId") final Long repertoireId, final Model model) {
        return reservationService.spectacleReservationSeatPage(spectacleName, repertoireId, model);
    }
    @Transactional
    @PostMapping(Routes.SEAT_RESERVATION)
    public String reserve(@ModelAttribute("seatInfo") final ReserveSeatConfiguration reserveSeatConfiguration,
                          @PathVariable("repertoireId") final Long repertoireId) {
        return reservationService.reservation(reserveSeatConfiguration, repertoireId);
    }
}
