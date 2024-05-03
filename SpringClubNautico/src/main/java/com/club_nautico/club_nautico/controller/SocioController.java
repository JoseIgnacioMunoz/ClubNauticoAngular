package com.club_nautico.club_nautico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.club_nautico.club_nautico.model.Socio;
import com.club_nautico.club_nautico.services.SocioService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/socios")
@RequiredArgsConstructor
public class SocioController {
    private final SocioService socioService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Socio>> obtenerTodosLosSocios() {
        List<Socio> socios = socioService.obtenerSocios();
        return ResponseEntity.ok(socios);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Socio> obtenerSocioPorId(@PathVariable("id") Long socioId) {
        Optional<Socio> socio = socioService.obtenerSocioPorId(socioId);
        if (socio.isPresent()) {
            return new ResponseEntity<>(socio.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/nombre")
    public ResponseEntity<List<Socio>> obtenerSociosPorNombre(@RequestParam String nombre) {
        List<Socio> socios = socioService.obtenerSocioPorNombre(nombre);
        if (!socios.isEmpty()) {
            return ResponseEntity.ok(socios);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<Socio> insertarSocio(@RequestBody Socio socio) {
        Socio nuevoSocio = socioService.crearSocio(socio);
        return ResponseEntity.status(201).body(nuevoSocio);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSocio(@PathVariable("id") Long socioId) {
        socioService.eliminarSocio(socioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<Socio> updateSocio(@PathVariable("id") Long socioId, @RequestBody Socio socio) {
        Optional<Socio> optionalSocio = socioService.obtenerSocioPorId(socioId);
        if (optionalSocio.isPresent()) {
            Socio socioExistente = optionalSocio.get();
            socioExistente.setNombre(socio.getNombre());
            socioExistente.setApellidos(socio.getApellidos());
            
            Socio socioActualizado = socioService.actualizarSocio(socioExistente); // Utilizamos un método específico para actualizar en el servicio
            return new ResponseEntity<>(socioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
