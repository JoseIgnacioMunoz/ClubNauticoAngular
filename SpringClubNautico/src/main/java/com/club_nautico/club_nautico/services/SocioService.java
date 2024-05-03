package com.club_nautico.club_nautico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club_nautico.club_nautico.model.Socio;
import com.club_nautico.club_nautico.repository.SocioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    // Método para obtener todos los socios
    public List<Socio> obtenerSocios() {
        return socioRepository.findAll();
    }

    // Método para obtener un socio por su ID
    public Optional<Socio> obtenerSocioPorId(Long socioId){
        return socioRepository.findById(socioId);
    }

    // Método para obtener socios por nombre
    public List<Socio> obtenerSocioPorNombre(String nombre){
        return socioRepository.findAllByNombre(nombre);
    }

    // Método para crear un nuevo socio
    public Socio crearSocio(Socio socio){
        // Creamos un nuevo socio con los datos proporcionados
        Socio nuevoSocio = new Socio();
        nuevoSocio.setNombre(socio.getNombre());
        nuevoSocio.setApellidos(socio.getApellidos());
        nuevoSocio.setBarcos(null); 
        
        // Guardamos el nuevo socio en la base de datos y lo retornamos
        return socioRepository.save(nuevoSocio);
    }

    // Método para eliminar un socio por su ID
    public void eliminarSocio(Long socioId){
        socioRepository.deleteById(socioId);
    }

    // Método para actualizar un socio existente
    public Socio actualizarSocio(Socio socio) {
        // Verificar si el socio existe en la base de datos
        Optional<Socio> optionalSocio = socioRepository.findById(socio.getId());
        if (optionalSocio.isPresent()) {
            // Actualizar los campos del socio 
            Socio socioExistente = optionalSocio.get();
            socioExistente.setNombre(socio.getNombre());
            socioExistente.setApellidos(socio.getApellidos());
            
            // Guardar el socio actualizado en la base de datos y retornarlo
            return socioRepository.save(socioExistente);
        } else {
            // Si el socio no existe, retornamos null
            return null;
        }
    }
}
