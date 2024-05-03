package com.club_nautico.club_nautico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.club_nautico.club_nautico.model.Barco;

public interface BarcoRepository extends JpaRepository<Barco, Long> {

    @Query("SELECT DISTINCT b FROM Barco b JOIN FETCH b.socio")
    List<Barco> findAllWithSocio();

    Optional<Barco> findByMatricula(Long matricula);
    
}
