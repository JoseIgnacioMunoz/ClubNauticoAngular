package com.club_nautico.club_nautico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club_nautico.club_nautico.model.Usuario;
import com.club_nautico.club_nautico.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario verificar(String username, String password) {
        // Implementa la lógica para autenticar al usuario basado en el nombre de
        // usuario y la contraseña

        Usuario usuario = usuarioRepository.findByNombre(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        } else {
            return null;
        }
    }
}
