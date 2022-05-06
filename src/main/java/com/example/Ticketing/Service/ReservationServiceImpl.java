package com.example.ticketing.service;

import com.example.ticketing.repository.ReservationRepository;
import com.example.ticketing.model.*;
import com.example.ticketing.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private static final List<String> rows = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

    private final RepertoireRepository repertoireRepository;
    private final MovieRepository movieRepository;
    private final SpectacleRepository spectacleRepository;
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;


    @Override
    public String showMovieReservationPage(final String movieName, final Model model) {
        final Movie movie = movieRepository.findByName(movieName);
        final List<Repertoire> repertoires = repertoireRepository.findByMovieId(movie.getId());
        model.addAttribute("repertoires", repertoires);
        return "reservation-movie";
    }

    @Override
    public String showSpectacleReservationPage(final String spectacleName, final Model model) {
        final Spectacle spectacle = spectacleRepository.findByTitle(spectacleName);
        final List<Repertoire> repertoires = repertoireRepository.findBySpectacleId(spectacle.getId());
        model.addAttribute("repertoires", repertoires);
        return "reservation-spectacle";
    }

    @Override
    public String showMovieReservationSeatPage(final String movieName, final Long repertoireId, final Model model) {
        reserveSeats(model, repertoireId);
        model.addAttribute("movieName", movieName);
        addRows(model, repertoireId);
        return "reservation-seat-movie";
    }

    @Override
    public String spectacleReservationSeatPage(final String spectacleName, final Long repertoireId, final Model model) {
        reserveSeats(model, repertoireId);
        model.addAttribute("spectacleName", spectacleName);
        addRows(model, repertoireId);
        return "reservation-seat-spectacle";
    }

    @Override
    public String reservation(final ReserveSeatConfiguration reserveSeatConfiguration, final Long repertoireId) {
        final List<String> reservedSeats = getReservedSeats(reserveSeatConfiguration);
        if (reservedSeats.size() > 0 && reservedSeats.size() <= 15) {
            final UUID uuid = UUID.randomUUID();
            final Ticket ticket = new Ticket();
            ticket.setSeat(String.join(",", reservedSeats));
            ticket.setUuid(uuid);
            ticketRepository.save(ticket);

            final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            final String email = principal.getUsername();
            final Optional<Users> byEmail = userRepository.findByEmail(email);
            final Reservation reservation = new Reservation();
            reservation.setTicket(ticketRepository.findByUuid(uuid).orElse(null));
            Repertoire repertoire = repertoireRepository.findById(repertoireId).orElse(null);
            try {
                if (repertoire != null) {
                    reservation.setMovie(movieRepository.findByName(repertoire.getMovie().getName()));
                }
            } catch (NullPointerException e) {
                if (repertoire.getSpectacle() != null) {
                    reservation.setSpectacle(spectacleRepository.findByTitle(repertoire.getSpectacle().getTitle()));
                }
            }
            reservation.setRepertoire(repertoire);
            reservation.setUsers(byEmail.get());
            reservation.setTicket(ticketRepository.getById(ticket.getId()));
            reservation.setSpectacle(spectacleRepository.getById(repertoire.getSpectacle().getId()));
            reservationRepository.save(reservation);
            return "successful";
        } else {
            return "unsuccessful";
        }
    }

    private void reserveSeats(final Model model, @PathVariable("repertoireId") final Long repertoireId) {
        final ReserveSeatConfiguration reserveSeatConfigurationMovie = new ReserveSeatConfiguration();
        final SeatReservation seatReservation = new SeatReservation();
        final Map<String, Boolean> mapMovie = new HashMap<>();
        getReservedSeats(repertoireId).forEach(seat -> mapMovie.put(seat, true));
        reserveSeatConfigurationMovie.setMap(mapMovie);
        reserveSeatConfigurationMovie.setSeatReservation(seatReservation);
        model.addAttribute("seatInfo", reserveSeatConfigurationMovie);
    }

    private Set<String> getReservedSeats(@PathVariable("repertoireId") final Long repertoireId) {
        final Repertoire repertoire = repertoireRepository.getOne(repertoireId);
        final Set<String> reservedSeats = new HashSet<>();
        for (final Reservation reservation : repertoire.getReservations()) {
            reservedSeats.addAll(Arrays.asList(reservation.getTicket().getSeat().split(",")));
        }
        log.info("Currently reserved seats:" + reservedSeats);
        return reservedSeats;
    }

    private void addRows(final Model model, final Long repertoireId) {
        model.addAttribute("repertoireId", repertoireId);
        model.addAttribute("rows", rows);
    }

    private List<String> getReservedSeats(@ModelAttribute("seatInfo") final ReserveSeatConfiguration reserveSeatConfiguration) {
        final List<String> reservedSeats = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : reserveSeatConfiguration.getMap().entrySet()) {
            if (TRUE.equals(entry.getValue())) {
                reservedSeats.add(entry.getKey());
            }
        }
        return reservedSeats;
    }
}
