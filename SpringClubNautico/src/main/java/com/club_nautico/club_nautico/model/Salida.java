package com.club_nautico.club_nautico.model;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    private String destino;
    
    @ManyToOne
    @JoinColumn(name = "barcoMatricula")
    private Barco barco;

    @ManyToOne
    @JoinColumn(name = "patron")
    private Patron patron;

}
