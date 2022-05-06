package com.example.ticketing.repository;

import com.example.ticketing.model.Spectacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectacleRepository extends JpaRepository<Spectacle, Long> {

    Spectacle findByTitle(String spectacleTitle);

    @Override
    Spectacle getById(Long spectacleId);

    ;
}
