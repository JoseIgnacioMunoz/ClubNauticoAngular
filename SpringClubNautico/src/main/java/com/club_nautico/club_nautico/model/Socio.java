package com.club_nautico.club_nautico.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    private String apellidos;

    @JsonManagedReference 
    @OneToMany(mappedBy = "socio", fetch = FetchType.EAGER)
    private List<Barco> barcos = new ArrayList<>();

    // Método para agregar un barco a la lista de barcos del socio
    public void agregarBarco(Barco barco) {
        // Verificar si la lista de barcos está inicializada
        if (barcos == null) {
            barcos = new ArrayList<>();
        }
        // Agregar el barco a la lista de barcos del socio
        barcos.add(barco);
        // Establecer el socio actual como el propietario del barco
        barco.setSocio(this);
    }
}
