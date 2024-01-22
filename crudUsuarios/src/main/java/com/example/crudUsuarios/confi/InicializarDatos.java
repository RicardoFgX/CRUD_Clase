package com.example.crudUsuarios.confi;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.crudUsuarios.entidades.Usuario;
import com.example.crudUsuarios.repositorios.UsuarioRepository;
import com.github.javafaker.Faker;

//Sirve para crear Usuarios de base cuando se inicia la aplicación
@Component
public class InicializarDatos implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        
        // Se establecen los datos ficticios para el primer usuario.
        usuario1.setNombre("Pedro");
        usuario1.setApellido("Perez");
        usuario1.setEmail("emailDEjemplo@gmail.com");
        usuario1.setFechaDeCreacion(LocalDateTime.now());
        
        // Se establecen los datos ficticios para el segundo usuario.
        usuario2.setNombre("Luis");
        usuario2.setApellido("Torres");
        usuario2.setEmail("emailDEjemplo2@gmail.com");
        usuario2.setFechaDeCreacion(LocalDateTime.now());

        // Se guardan los objetos Usuario en la base de datos utilizando el repositorio.
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
    }
}
