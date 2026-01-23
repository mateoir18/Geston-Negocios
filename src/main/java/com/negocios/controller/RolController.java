package com.negocios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.negocios.dto.RolDTO;
import com.negocios.mapper.RolMapper;
import com.negocios.model.Rol;
import com.negocios.repository.RolRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolController {

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private RolMapper rolMapper;

	// Obtener todos los roles
	@GetMapping
	public ResponseEntity<List<RolDTO>> getAllRoles() {
		List<RolDTO> roles = rolRepository.findAll().stream().map(rolMapper::toDTO).collect(Collectors.toList());
		return ResponseEntity.ok(roles);
	}

	// Obtener un rol por ID
	@GetMapping("/{id}")
	public ResponseEntity<RolDTO> getRolById(@PathVariable Long id) {
		return rolRepository.findById(id).map(rolMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Crear un nuevo rol
	@PostMapping
	public ResponseEntity<RolDTO> createRol(@Valid @RequestBody Rol rol) {
		Rol nuevoRol = rolRepository.save(rol);
		return ResponseEntity.status(HttpStatus.CREATED).body(rolMapper.toDTO(nuevoRol));
	}

	// Buscar rol por nombre
	@GetMapping("/nombre/{nombreRol}")
	public ResponseEntity<RolDTO> getRolByNombre(@PathVariable Rol.NombreRol nombreRol) {
		return rolRepository.findByNombreRol(nombreRol).map(rolMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}