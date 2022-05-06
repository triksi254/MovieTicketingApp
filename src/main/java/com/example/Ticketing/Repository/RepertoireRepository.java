package com.example.ticketing.repository;

import com.example.ticketing.model.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {
    List<Repertoire> findBySpectacleId(Long id);

    List<Repertoire> findByMovieId(Long id);
}
