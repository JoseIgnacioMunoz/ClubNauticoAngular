package com.club_nautico.club_nautico.controller;

import com.club_nautico.club_nautico.model.Salida;
import com.club_nautico.club_nautico.services.SalidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salidas")
public class SalidaController {

    private final SalidaService salidaService;

    @Autowired
    public SalidaController(SalidaService salidaService) {
        this.salidaService = salidaService;
    }

    // Endpoint para obtener todas las salidas
    @GetMapping
    public List<Salida> obtenerTodasLasSalidas() {
        return salidaService.obtenerTodasLasSalidas();
    }

    // Endpoint para obtener una salida por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Salida> obtenerSalidaPorId(@PathVariable Long id) {
        Optional<Salida> salida = salidaService.obtenerSalidaPorId(id);
        if (salida.isPresent()) {
            return ResponseEntity.ok(salida.get()); // Retorna la salida si se encuentra
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra la salida
        }
    }

    // Endpoint para crear una nueva salida
    @PostMapping
    public ResponseEntity<Salida> crearSalida(@RequestBody Salida salida) {
        Salida nuevaSalida = salidaService.crearSalida(salida);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSalida); // Retorna 201 y la salida creada
    }

    @PutMapping("/{salidaId}/asociar")
    public ResponseEntity<Salida> asociarBarcoYPatron(@PathVariable Long salidaId, @RequestParam Long barcoId, @RequestParam Long patronId) {
        Salida salida = salidaService.asociarBarcoYPatron(salidaId, barcoId, patronId);
        return salida != null ? ResponseEntity.ok(salida) : ResponseEntity.notFound().build();
    }

    // Endpoint para actualizar una salida existente
    @PutMapping("/{id}")
    public ResponseEntity<Salida> actualizarSalida(@PathVariable Long id, @RequestBody Salida salida) {
        Salida salidaActualizada = salidaService.actualizarSalida(id, salida);
        if (salidaActualizada != null) {
            return ResponseEntity.ok(salidaActualizada); // Retorna la salida actualizada si se encuentra
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra la salida
        }
    }

    // Endpoint para eliminar una salida por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSalida(@PathVariable Long id) {
        salidaService.eliminarSalida(id);
        return ResponseEntity.noContent().build(); // Retorna 204 si se elimina con éxito
    }
}
