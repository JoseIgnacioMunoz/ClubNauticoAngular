package com.club_nautico.club_nautico.services;

import com.club_nautico.club_nautico.model.Barco;
import com.club_nautico.club_nautico.model.Patron;
import com.club_nautico.club_nautico.model.Salida;
import com.club_nautico.club_nautico.repository.BarcoRepository;
import com.club_nautico.club_nautico.repository.PatronRepository;
import com.club_nautico.club_nautico.repository.SalidaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalidaService {

    private final SalidaRepository salidaRepository;
    private final BarcoRepository barcoRepository;
    private final PatronRepository patronRepository;

    // Método para obtener todas las salidas
    public List<Salida> obtenerTodasLasSalidas() {
        return salidaRepository.findAll();
    }

    // Método para obtener una salida por su ID
    public Optional<Salida> obtenerSalidaPorId(Long id) {
        return salidaRepository.findById(id);
    }

    // Método para crear una nueva salida
    public Salida crearSalida(Salida salida) {
        // Creamos una nueva salida con los datos proporcionados
        Salida nuevaSalida = new Salida();
        nuevaSalida.setFechaSalida(salida.getFechaSalida());
        nuevaSalida.setDestino(salida.getDestino());
        nuevaSalida.setBarco(null); 
        nuevaSalida.setPatron(null); 
        
        // Guardamos la salida en la base de datos y lo retornamos
        return salidaRepository.save(nuevaSalida);
    }

    public Salida asociarBarcoYPatron(Long salidaId, Long barcoId, Long patronId) {
        // Busca la salida, el barco y el patrón correspondientes a los IDs proporcionados
        Optional<Salida> optionalSalida = salidaRepository.findById(salidaId);
        Optional<Barco> optionalBarco = barcoRepository.findById(barcoId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);

        if (optionalSalida.isPresent() && optionalBarco.isPresent() && optionalPatron.isPresent()) {
            Salida salida = optionalSalida.get();
            salida.setBarco(optionalBarco.get());
            salida.setPatron(optionalPatron.get());
            return salidaRepository.save(salida);
        } else {
            return null;
        }
    }

    // Método para actualizar una salida existente
    public Salida actualizarSalida(Long id, Salida salida) {
        // Buscar la salida en la base de datos por su ID
        Optional<Salida> optionalSalida = salidaRepository.findById(id);
        if (optionalSalida.isPresent()) { // Si se encuentra la salida
            Salida salidaExistente = optionalSalida.get();
            // Actualizar los campos de la salida existente con los datos proporcionados
            salidaExistente.setFechaSalida(salida.getFechaSalida());
            salidaExistente.setDestino(salida.getDestino());
            salidaExistente.setBarco(salida.getBarco());
            salidaExistente.setPatron(salida.getPatron());
            return salidaRepository.save(salidaExistente); // Guardar y retornar la salida actualizada
        } else { // Si no se encuentra la salida
            return null; // Retorna null si la salida no se encuentra en la base de datos
        }
    }

    // Eliminar una salida por su ID
    public void eliminarSalida(Long id) {
        salidaRepository.deleteById(id);
    }
}
