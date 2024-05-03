package com.club_nautico.club_nautico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club_nautico.club_nautico.model.Patron;
import com.club_nautico.club_nautico.repository.PatronRepository;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> obtenerTodosLosPatrones() {
        return patronRepository.findAll();
    }

    public Optional<Patron> obtenerPatronPorId(Long id) {
        return patronRepository.findById(id);
    }

    public Patron crearPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron actualizarPatron(Long id, Patron patron) {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron patronExistente = optionalPatron.get();
            patronExistente.setNombre(patron.getNombre());
            patronExistente.setApellidos(patron.getApellidos());
            patronExistente.setTelefono(patron.getTelefono());
            patronExistente.setEmail(patron.getEmail());
            return patronRepository.save(patronExistente);
        } else {
            return null;
        }
    }

    public void eliminarPatron(Long id) {
        patronRepository.deleteById(id);
    }
}
