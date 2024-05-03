package com.club_nautico.club_nautico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.club_nautico.club_nautico.model.Barco;
import com.club_nautico.club_nautico.services.BarcoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/barcos")
public class BarcoController {

    private final BarcoService barcoService;

    @GetMapping
    public ResponseEntity<List<Barco>> obtenerTodosLosBarcos() {
        List<Barco> barcos = barcoService.obtenerBarcosConSocios();
        return ResponseEntity.ok(barcos);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Barco> obtenerBarcoPorMatricula(@PathVariable("matricula") Long matricula) {
        Optional<Barco> barco = barcoService.obtenerBarcoPorMatricula(matricula);
        if (barco.isPresent()) {
            return ResponseEntity.ok(barco.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Barco> crearBarco(@RequestBody Barco barco, @RequestParam Long socioId) {
        try {
            Barco nuevoBarco = barcoService.crearBarco(barco); // Crear el barco
            // Asociar el barco con el socio
            barcoService.asociarBarcoConSocio(nuevoBarco, socioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBarco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PutMapping("/{matricula}")
    public ResponseEntity<Barco> actualizarBarco(@PathVariable Long matricula, @RequestBody Barco barco, @RequestParam(required = false) Long socioId) {
        try {
            if (socioId != null) {
                // Si se proporciona socioId, asociar el barco con el socio correspondiente
                Barco barcoActualizado = barcoService.actualizarBarcoConSocio(matricula, barco, socioId);
                return ResponseEntity.ok(barcoActualizado);
            } else {
                // Si no se proporciona socioId, simplemente actualizar el barco sin cambiar el socio
                Barco barcoActualizado = barcoService.actualizarBarco(matricula, barco);
                return ResponseEntity.ok(barcoActualizado);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> eliminarBarco(@PathVariable Long matricula) {
        barcoService.eliminarBarco(matricula);
        return ResponseEntity.noContent().build();
    }
}
