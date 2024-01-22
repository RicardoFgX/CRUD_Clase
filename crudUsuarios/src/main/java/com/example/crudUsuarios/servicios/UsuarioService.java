package com.example.crudUsuarios.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudUsuarios.entidades.Usuario;
import com.example.crudUsuarios.repositorios.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por su ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Obtener un usuario por su email (posible mejora)
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setFechaDeCreacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario existente por su ID
    public Usuario actualizarUsuario(Long id, Usuario nuevoUsuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNombre(nuevoUsuario.getNombre());
            usuario.setApellido(nuevoUsuario.getApellido());
            // Puedes agregar actualizaciones de otros campos según sea necesario
            return usuarioRepository.save(usuario);
        } else {
            throw new NoSuchElementException("Usuario no encontrado");
        }
    }

    // Eliminar un usuario por su ID
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
