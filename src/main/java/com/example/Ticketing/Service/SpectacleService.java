package com.example.ticketing.service;

import com.example.ticketing.model.Repertoire;
import com.example.ticketing.model.Spectacle;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface SpectacleService {
    String getSpectacles(Model model);

    String addSpectacle(Spectacle spectacle, BindingResult result, Model model);

    String showUpdateFormSpectacle(long id, Model model);

    String updateSpectacle(long id, Spectacle spectacle);

    String deleteSpectacle(long id, Model model);

    String showSpectacleRepertoireForm(String spectacleName, Model model);

    String addSpectacleRepertoire(Repertoire repertoire, Long spectacleId, BindingResult result);

    String showUpdateSpectacleRepertoireForm(String spectacleName, Long repertoireId, Model model);

    String updateSpectacleRepertoire(Repertoire repertoire, Long repertoireId, BindingResult result);

    String deleteSpectacleRepertoire(Long repertoireId, Model model);
}
