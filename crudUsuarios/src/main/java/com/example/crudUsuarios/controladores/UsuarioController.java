package com.example.crudUsuarios.controladores;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudUsuarios.entidades.Usuario;
import com.example.crudUsuarios.servicios.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	//Solicitud GET que devuelve una lista con todos los Usuarios
	@GetMapping
	public List<Usuario> obtenerTodosUsuarios() {
		return usuarioService.obtenerTodosUsuarios();
	}
	
	//Solicitud GET que devuelve un Usuario por ID
	@GetMapping("/{id}")
	 public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
	     java.util.Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
	     return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	 }

	//Solicitud POST que crea a un Nuevo Usuario en la base de datos. Se pasa por parámetro el nuevo usuario
	 @PostMapping
	 public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
	     Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
	     return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	 }

	 //Solicitud PUT que actualiza a un Usuario existente con una ID concreta en la base de datos. Se pasa por parámetro el Usuario actualizado
	 @PutMapping("/{id}")
	 public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario nuevoUsuario) {
	     try {
	         Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, nuevoUsuario);
	         return ResponseEntity.ok(usuarioActualizado);
	     } catch (NoSuchElementException e) {
	         return ResponseEntity.notFound().build();
	     }
	 }

	 //Solicitud DELETE que borra a un Usuario con una ID concreta de la base de datos
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
	     usuarioService.eliminarUsuario(id);
	     return ResponseEntity.noContent().build();
	 }
}
