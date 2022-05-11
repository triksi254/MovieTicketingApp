package com.example.ticketing.repository;

import com.example.ticketing.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    Optional<Ticket> findByUuid(UUID uuid);

    Ticket getById(Long id);
}
