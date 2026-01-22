package com.negocios.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.negocios.model.Usuario;
import com.negocios.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Obtener todos los usuarios
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return ResponseEntity.ok(usuarios);
	}

	// Obtener usuario por ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable UUID id) {
		return usuarioRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Obtener usuarios por negocio
	@GetMapping("/negocio/{negocioId}")
	public ResponseEntity<List<Usuario>> getUsuariosByNegocio(@PathVariable UUID negocioId) {
		List<Usuario> usuarios = usuarioRepository.findByNegocioId(negocioId);
		return ResponseEntity.ok(usuarios);
	}

	// Crear un nuevo usuario
	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {
		// Verificar si ya existe un usuario con ese email
		if (usuarioRepository.existsByEmail(usuario.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	}

	// Actualizar un usuario
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable UUID id,
			@Valid @RequestBody Usuario usuarioActualizado) {
		return usuarioRepository.findById(id).map(usuario -> {
			usuario.setEmail(usuarioActualizado.getEmail());
			usuario.setNombreCompleto(usuarioActualizado.getNombreCompleto());
			// Solo actualizar la contraseña si se proporciona
			if (usuarioActualizado.getPasswordHash() != null && !usuarioActualizado.getPasswordHash().isEmpty()) {
				usuario.setPasswordHash(usuarioActualizado.getPasswordHash());
			}
			Usuario actualizado = usuarioRepository.save(usuario);
			return ResponseEntity.ok(actualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Eliminar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
		return usuarioRepository.findById(id).map(usuario -> {
			usuarioRepository.delete(usuario);
			return ResponseEntity.ok().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}

	// Buscar usuario por email
	@GetMapping("/email/{email}")
	public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable String email) {
		return usuarioRepository.findByEmail(email).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
}