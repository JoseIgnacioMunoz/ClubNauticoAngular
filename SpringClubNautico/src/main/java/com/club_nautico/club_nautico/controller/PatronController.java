package com.club_nautico.club_nautico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.club_nautico.club_nautico.model.Patron;
import com.club_nautico.club_nautico.services.PatronService;

@RestController
@RequestMapping("/patrones")
public class PatronController {

    private final PatronService patronService;

    // Constructor que inyecta el servicio de patron
    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    // Endpoint para obtener todos los patrones
    @GetMapping
    public List<Patron> obtenerTodosLosPatrones() {
        return patronService.obtenerTodosLosPatrones(); // Retorna la lista de todos los patrones
    }

    // Endpoint para obtener un patrón por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Patron> obtenerPatronPorId(@PathVariable("id") Long id) {
        Optional<Patron> patron = patronService.obtenerPatronPorId(id); // Obtiene el patrón por su ID
        if (patron.isPresent()) {
            return ResponseEntity.ok(patron.get()); // Retorna el patrón si existe
        } else {
            return ResponseEntity.notFound().build(); // Retorna un 404 si no se encuentra el patrón
        }
    }

    // Endpoint para crear un nuevo patrón
    @PostMapping
    public ResponseEntity<Patron> crearPatron(@RequestBody Patron patron) {
        Patron nuevoPatron = patronService.crearPatron(patron); // Crea un nuevo patrón
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPatron); // Retorna el nuevo patrón creado
    }

    // Endpoint para actualizar un patrón existente
    @PutMapping("/{id}")
    public ResponseEntity<Patron> actualizarPatron(@PathVariable("id") Long id, @RequestBody Patron patron) {
        Patron patronActualizado = patronService.actualizarPatron(id, patron); // Actualiza el patrón con el ID dado
        if (patronActualizado != null) {
            return ResponseEntity.ok(patronActualizado); // Retorna el patrón actualizado si existe
        } else {
            return ResponseEntity.notFound().build(); // Retorna un 404 si no se encuentra el patrón para actualizar
        }
    }

    // Endpoint para eliminar un patrón por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPatron(@PathVariable("id") Long id) {
        patronService.eliminarPatron(id); // Elimina el patrón con el ID dado
        return ResponseEntity.noContent().build(); // Retorna un 204 (No Content) después de la eliminación
    }
}
