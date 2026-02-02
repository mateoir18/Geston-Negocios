package com.negocios.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.negocios.dto.UsuarioDTO;
import com.negocios.mapper.UsuarioMapper;
import com.negocios.model.Usuario;
import com.negocios.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;

	// Obtener todos los usuarios
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
		List<UsuarioDTO> usuarios = usuarioRepository.findAll().stream().map(usuarioMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(usuarios);
	}

	// Obtener usuario por ID
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable UUID id) {
		return usuarioRepository.findById(id).map(usuarioMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Obtener usuarios por negocio
	@GetMapping("/negocio/{negocioId}")
	public ResponseEntity<List<UsuarioDTO>> getUsuariosByNegocio(@PathVariable UUID negocioId) {
		List<UsuarioDTO> usuarios = usuarioRepository.findByNegocioId(negocioId).stream().map(usuarioMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(usuarios);
	}

	// Actualizar un usuario
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable UUID id,
			@RequestBody Usuario usuarioActualizado) { // QUITAMOS @Valid
		return usuarioRepository.findById(id).map(usuario -> {
			usuario.setEmail(usuarioActualizado.getEmail());
			usuario.setNombreCompleto(usuarioActualizado.getNombreCompleto());
			
			// Solo actualizar el rol si se proporciona
			if (usuarioActualizado.getRol() != null) {
				usuario.setRol(usuarioActualizado.getRol());
			}
			
			// Solo actualizar la contraseña si se proporciona y no está vacía
			if (usuarioActualizado.getPasswordHash() != null 
					&& !usuarioActualizado.getPasswordHash().isEmpty()
					&& !usuarioActualizado.getPasswordHash().equals("null")) {
				usuario.setPasswordHash(usuarioActualizado.getPasswordHash());
			}
			
			Usuario actualizado = usuarioRepository.save(usuario);
			return ResponseEntity.ok(usuarioMapper.toDTO(actualizado));
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
	public ResponseEntity<UsuarioDTO> getUsuarioByEmail(@PathVariable String email) {
		return usuarioRepository.findByEmail(email).map(usuarioMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}