package com.club_nautico.club_nautico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.club_nautico.club_nautico.model.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long>{
    //List<Socio> findAllByNombre(String nombre);

    @Query("SELECT s FROM Socio s WHERE s.nombre = :nombre")
    List<Socio> findAllByNombre(@Param("nombre") String nombre);
}
