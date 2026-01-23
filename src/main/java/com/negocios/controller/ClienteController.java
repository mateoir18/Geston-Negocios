package com.negocios.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.negocios.dto.ClienteDTO;
import com.negocios.mapper.ClienteMapper;
import com.negocios.model.Cliente;
import com.negocios.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteMapper clienteMapper;

	// Obtener todos los clientes
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAllClientes() {
		List<ClienteDTO> clientes = clienteRepository.findAll().stream().map(clienteMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(clientes);
	}

	// Obtener cliente por ID
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getClienteById(@PathVariable UUID id) {
		return clienteRepository.findById(id).map(clienteMapper::toDTO).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Obtener clientes por negocio
	@GetMapping("/negocio/{negocioId}")
	public ResponseEntity<List<ClienteDTO>> getClientesByNegocio(@PathVariable UUID negocioId) {
		List<ClienteDTO> clientes = clienteRepository.findByNegocioId(negocioId).stream().map(clienteMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(clientes);
	}

	// Crear un nuevo cliente
	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody Cliente cliente) {
		Cliente nuevoCliente = clienteRepository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.toDTO(nuevoCliente));
	}

	// Actualizar un cliente
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> updateCliente(@PathVariable UUID id,
			@Valid @RequestBody Cliente clienteActualizado) {
		return clienteRepository.findById(id).map(cliente -> {
			cliente.setNombre(clienteActualizado.getNombre());
			cliente.setTelefono(clienteActualizado.getTelefono());
			cliente.setEmail(clienteActualizado.getEmail());
			Cliente actualizado = clienteRepository.save(cliente);
			return ResponseEntity.ok(clienteMapper.toDTO(actualizado));
		}).orElse(ResponseEntity.notFound().build());
	}

	// Eliminar un cliente
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable UUID id) {
		return clienteRepository.findById(id).map(cliente -> {
			clienteRepository.delete(cliente);
			return ResponseEntity.ok().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}

	// Buscar clientes por nombre
	@GetMapping("/buscar/{nombre}/negocio/{negocioId}")
	public ResponseEntity<List<ClienteDTO>> buscarClientesPorNombre(@PathVariable String nombre,
			@PathVariable UUID negocioId) {
		List<ClienteDTO> clientes = clienteRepository.findByNombreContainingIgnoreCaseAndNegocioId(nombre, negocioId)
				.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
		return ResponseEntity.ok(clientes);
	}
}
