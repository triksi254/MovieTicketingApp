package com.example.ticketing.controller;

import com.example.ticketing.model.Repertoire;
import com.example.ticketing.model.Spectacle;
import com.example.ticketing.service.SpectacleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@NoArgsConstructor
@RequestMapping
public class SpectacleController {
    static final class Routes {
        static final String ROOT = "/spectacles";
        static final String USER = ROOT + "/user";
        static final String SPECTACLE_NAME = USER + "/{spectacleName}";
        static final String LIST = ROOT + "/list";
        static final String FORM = ROOT + "/showForm";
        static final String ADD = ROOT + "/add";
        static final String EDIT = ROOT + "/edit/{id}";
        static final String UPDATE = ROOT + "/update/{id}";
        static final String DELETE = ROOT + "/delete/{id}";
        static final String ADD_REPERTOIRE = USER + "/newRepertoire";
        static final String NEW_REPERTOIRE = SPECTACLE_NAME + "/newRepertoire";
        static final String UPDATE_REPERTOIRE_ID = SPECTACLE_NAME + "/updateRepertoire/{repertoireId}";
        static final String UPDATE_REPERTOIRE = USER + "/updateRepertoire";
        static final String DELETE_REPERTOIRE = USER + "/deleteRepertoire/{repertoireId}";
    }
    @Autowired
    SpectacleService spectacleService;

    @GetMapping(Routes.LIST)
    public String getSpectacles(final Model model) {
        return spectacleService.getSpectacles(model);
    }

    @GetMapping(Routes.FORM)
    public String showSpectacleForm(final Spectacle spectacle) {
        return "/add-spectacle";
    }

    @PostMapping(Routes.ADD)
    public String addSpectacle(@Validated final Spectacle spectacle, final BindingResult result, final Model model) {
        return spectacleService.addSpectacle(spectacle, result, model);
    }

    @GetMapping(Routes.EDIT)
    public String showUpdateFormSpectacle(@PathVariable("id") final long id, final Model model) {
        return spectacleService.showUpdateFormSpectacle(id, model);
    }

    @PostMapping(Routes.UPDATE)
    @Transactional
    public String updateSpectacle(@PathVariable("id") final long id, @Validated final Spectacle spectacle) {
        return spectacleService.updateSpectacle(id, spectacle);
    }

    @GetMapping(Routes.DELETE)
    public String deleteSpectacle(@PathVariable("id") final long id, final Model model) {
        return spectacleService.deleteSpectacle(id, model);
    }

    @GetMapping(Routes.NEW_REPERTOIRE)
    public String showSpectacleRepertoireForm(@PathVariable("spectacleName") final String spectacleName, final Model model) {
        return spectacleService.showSpectacleRepertoireForm(spectacleName, model);
    }

    @PostMapping(Routes.ADD_REPERTOIRE)
    @Transactional
    public String addSpectacleRepertoire(@ModelAttribute("repertoire") final Repertoire repertoire,
                                         @ModelAttribute("spectacleId") final Long spectacleId, final BindingResult result) {
        return spectacleService.addSpectacleRepertoire(repertoire, spectacleId, result);
    }

    @GetMapping(Routes.UPDATE_REPERTOIRE_ID)
    public String showUpdateSpectacleRepertoireForm(@PathVariable("spectacleName") final String spectacleName,
                                                    @PathVariable("repertoireId") final Long repertoireId, final Model model) {
        return spectacleService.showUpdateSpectacleRepertoireForm(spectacleName, repertoireId, model);
    }

    @PostMapping(Routes.UPDATE_REPERTOIRE)
    @Transactional
    public String updateSpectacleRepertoire(@ModelAttribute("repertoire") final Repertoire repertoire,
                                            @ModelAttribute("spectacleId") final Long spectacleId,
                                            @ModelAttribute("repertoireId") final Long repertoireId, final BindingResult result) {
        return spectacleService.updateSpectacleRepertoire(repertoire, repertoireId, result);
    }

    @GetMapping(Routes.DELETE_REPERTOIRE)
    @Transactional
    public String deleteSpectacleRepertoire(@PathVariable("repertoireId") final Long repertoireId, final Model model) {
        return spectacleService.deleteSpectacleRepertoire(repertoireId, model);
    }
}
