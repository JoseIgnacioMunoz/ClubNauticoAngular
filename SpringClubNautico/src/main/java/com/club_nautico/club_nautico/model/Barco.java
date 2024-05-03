package com.club_nautico.club_nautico.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Barco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long matricula;

    private String nombre;
    private Integer numAmarre;
    private Float cuota; // Aunque es menos preciso, Float ocupa la mitad de espacio en memoria que Double

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "socioId")
    private Socio socio;

    @OneToMany(mappedBy = "barco")
    private List<Salida> salidas = new ArrayList<>();
}
    