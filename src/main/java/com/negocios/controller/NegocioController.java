package com.negocios.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.negocios.dto.NegocioDTO;
import com.negocios.mapper.NegocioMapper;
import com.negocios.model.Negocio;
import com.negocios.repository.NegocioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/negocios")
@CrossOrigin(origins = "*")
public class NegocioController {

	@Autowired
	private NegocioRepository negocioRepository;

	@Autowired
	private NegocioMapper negocioMapper;

	// Obtener todos los negocios
	@GetMapping
	public ResponseEntity<List<NegocioDTO>> getAllNegocios() {
		List<NegocioDTO> negocios = negocioRepository.findAll().stream().map(negocioMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(negocios);
	}

	// Obtener un negocio por ID
	@GetMapping("/{id}")
	public ResponseEntity<NegocioDTO> getNegocioById(@PathVariable UUID id) {
		return negocioRepository.findById(id).map(negocioMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Crear un nuevo negocio
	@PostMapping
	public ResponseEntity<NegocioDTO> createNegocio(@Valid @RequestBody Negocio negocio) {
		// Verificar si ya existe un negocio con ese CIF/NIF
		if (negocioRepository.existsByCifNif(negocio.getCifNif())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		Negocio nuevoNegocio = negocioRepository.save(negocio);
		return ResponseEntity.status(HttpStatus.CREATED).body(negocioMapper.toDTO(nuevoNegocio));
	}

	// Actualizar un negocio
	@PutMapping("/{id}")
	public ResponseEntity<NegocioDTO> updateNegocio(@PathVariable UUID id,
			@Valid @RequestBody Negocio negocioActualizado) {
		return negocioRepository.findById(id).map(negocio -> {
			negocio.setNombre(negocioActualizado.getNombre());
			negocio.setCifNif(negocioActualizado.getCifNif());
			negocio.setDireccion(negocioActualizado.getDireccion());
			Negocio actualizado = negocioRepository.save(negocio);
			return ResponseEntity.ok(negocioMapper.toDTO(actualizado));
		}).orElse(ResponseEntity.notFound().build());
	}

	// Eliminar un negocio
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNegocio(@PathVariable UUID id) {
		return negocioRepository.findById(id).map(negocio -> {
			negocioRepository.delete(negocio);
			return ResponseEntity.ok().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}

	// Buscar negocio por CIF/NIF
	@GetMapping("/cif/{cifNif}")
	public ResponseEntity<NegocioDTO> getNegocioByCifNif(@PathVariable String cifNif) {
		return negocioRepository.findByCifNif(cifNif).map(negocioMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}