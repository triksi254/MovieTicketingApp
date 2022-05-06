package com.example.ticketing.service;

import com.example.ticketing.model.Repertoire;
import com.example.ticketing.model.ReserveSeatConfiguration;
import com.example.ticketing.model.Spectacle;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;

public interface ReservationService {


    String showMovieReservationPage(String movieName, Model model);

    String showSpectacleReservationPage(String spectacleName, Model model);

    String showMovieReservationSeatPage(String movieName, Long repertoireId, Model model);

    String spectacleReservationSeatPage(String spectacleName, Long repertoireId, Model model);

    String reservation(ReserveSeatConfiguration reserveSeatConfiguration, Long repertoireId);
}
