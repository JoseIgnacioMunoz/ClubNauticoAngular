package com.club_nautico.club_nautico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.club_nautico.club_nautico.model.Barco;
import com.club_nautico.club_nautico.model.Socio;
import com.club_nautico.club_nautico.repository.BarcoRepository;
import com.club_nautico.club_nautico.repository.SocioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarcoService {

    private final BarcoRepository barcoRepository;
    private final SocioRepository socioRepository;


    public List<Barco> obtenerBarcosConSocios() {
        return barcoRepository.findAllWithSocio();
    }

    public Optional<Barco> obtenerBarcoPorMatricula(Long matricula) {
        return barcoRepository.findByMatricula(matricula);
    }

    // Método para crear un barco
    public Barco crearBarco(Barco barco) {
        return barcoRepository.save(barco);
    }

    // Método para asociar un barco con un socio
    public void asociarBarcoConSocio(Barco barco, Long socioId) throws Exception {
        Optional<Socio> socioOptional = socioRepository.findById(socioId);
        if (socioOptional.isPresent()) {
            Socio socio = socioOptional.get();
            // Asignar el Socio al Barco
            barco.setSocio(socio);
            // Agregar el Barco a la lista de Barcos del Socio
            socio.agregarBarco(barco);
            // Guardar el Socio actualizado con el nuevo Barco
            socioRepository.save(socio);
        } else {
            throw new Exception("No se encontró ningún socio con el ID proporcionado: " + socioId);
        }
    }

    public void eliminarBarco(Long matricula) {
        barcoRepository.deleteById(matricula);
    }

    public Barco actualizarBarco(Long matricula, Barco barco) {
        Optional<Barco> optionalBarco = barcoRepository.findById(matricula);
        if (optionalBarco.isPresent()) {
            Barco barcoExistente = optionalBarco.get();
            barcoExistente.setNombre(barco.getNombre());
            barcoExistente.setNumAmarre(barco.getNumAmarre());
            barcoExistente.setCuota(barco.getCuota());
            // Si el barco pasado por parámetro tiene un socio, lo establecemos como el socio del barco existente
            if (barco.getSocio() != null) {
                barcoExistente.setSocio(barco.getSocio());
            }
            return barcoRepository.save(barcoExistente);
        } else {
            return null;
        }
    }
    
    public Barco actualizarBarcoConSocio(Long matricula, Barco barco, Long socioId) {
        Optional<Barco> optionalBarco = barcoRepository.findById(matricula);
        if (optionalBarco.isPresent()) {
            Barco barcoExistente = optionalBarco.get();
            barcoExistente.setNombre(barco.getNombre());
            barcoExistente.setNumAmarre(barco.getNumAmarre());
            barcoExistente.setCuota(barco.getCuota());
            
            // Verificar si se proporcionó un socioId
            if (socioId != null) {
                // Obtener el socio por su ID y establecerlo como el nuevo socio del barco
                Optional<Socio> optionalSocio = socioRepository.findById(socioId);
                if (optionalSocio.isPresent()) {
                    Socio socio = optionalSocio.get();
                    barcoExistente.setSocio(socio);
                } else {
                    // Manejar el caso donde no se encuentre el socio con el ID proporcionado
                    return null;
                }
            }
            
            return barcoRepository.save(barcoExistente);
        } else {
            return null;
        }
    }
}
