package com.negocios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.negocios.model.Rol;
import com.negocios.repository.RolRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolController {

	@Autowired
	private RolRepository rolRepository;

	// Obtener todos los roles
	@GetMapping
	public ResponseEntity<List<Rol>> getAllRoles() {
		List<Rol> roles = rolRepository.findAll();
		return ResponseEntity.ok(roles);
	}

	// Obtener un rol por ID
	@GetMapping("/{id}")
	public ResponseEntity<Rol> getRolById(@PathVariable Long id) {
		return rolRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Crear un nuevo rol
	@PostMapping
	public ResponseEntity<Rol> createRol(@Valid @RequestBody Rol rol) {
		Rol nuevoRol = rolRepository.save(rol);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
	}

	// Buscar rol por nombre
	@GetMapping("/nombre/{nombreRol}")
	public ResponseEntity<Rol> getRolByNombre(@PathVariable Rol.NombreRol nombreRol) {
		return rolRepository.findByNombreRol(nombreRol).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}