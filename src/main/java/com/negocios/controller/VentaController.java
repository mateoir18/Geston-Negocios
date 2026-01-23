package com.negocios.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.negocios.dto.VentaDTO;
import com.negocios.mapper.VentaMapper;
import com.negocios.model.Venta;
import com.negocios.repository.VentaRepository;
import com.negocios.service.VentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private VentaService ventaService;

	@Autowired
	private VentaMapper ventaMapper;

	// Obtener todas las ventas
	@GetMapping
	public ResponseEntity<List<VentaDTO>> getAllVentas() {
		List<VentaDTO> ventas = ventaService.obtenerTodasLasVentas();
		return ResponseEntity.ok(ventas);
	}

	// Obtener venta por ID
	@GetMapping("/{id}")
	public ResponseEntity<VentaDTO> getVentaById(@PathVariable UUID id) {
		VentaDTO venta = ventaService.obtenerVentaPorId(id);
		if (venta != null) {
			return ResponseEntity.ok(venta);
		}
		return ResponseEntity.notFound().build();
	}

	// Obtener ventas por negocio
	@GetMapping("/negocio/{negocioId}")
	public ResponseEntity<List<VentaDTO>> getVentasByNegocio(@PathVariable UUID negocioId) {
		List<VentaDTO> ventas = ventaService.obtenerVentasPorNegocio(negocioId);
		return ResponseEntity.ok(ventas);
	}

	// Obtener ventas por cliente
	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<List<VentaDTO>> getVentasByCliente(@PathVariable UUID clienteId) {
		List<VentaDTO> ventas = ventaRepository.findByClienteId(clienteId).stream().map(ventaMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(ventas);
	}

	// Obtener ventas por usuario (empleado)
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<VentaDTO>> getVentasByUsuario(@PathVariable UUID usuarioId) {
		List<VentaDTO> ventas = ventaRepository.findByUsuarioId(usuarioId).stream().map(ventaMapper::toDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(ventas);
	}

	// Crear una nueva venta
	@PostMapping
	public ResponseEntity<VentaDTO> createVenta(@Valid @RequestBody Venta venta) {
		VentaDTO nuevaVenta = ventaService.crearVenta(venta);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
	}

	// Actualizar una venta
	@PutMapping("/{id}")
	public ResponseEntity<VentaDTO> updateVenta(@PathVariable UUID id, @Valid @RequestBody Venta ventaActualizada) {
		return ventaRepository.findById(id).map(venta -> {
			venta.setTotalVenta(ventaActualizada.getTotalVenta());
			venta.setCliente(ventaActualizada.getCliente());
			Venta actualizada = ventaRepository.save(venta);
			return ResponseEntity.ok(ventaMapper.toDTO(actualizada));
		}).orElse(ResponseEntity.notFound().build());
	}

	// Eliminar una venta
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVenta(@PathVariable UUID id) {
		return ventaRepository.findById(id).map(venta -> {
			ventaRepository.delete(venta);
			return ResponseEntity.ok().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}

	// Obtener ventas del día
	@GetMapping("/hoy/negocio/{negocioId}")
	public ResponseEntity<List<VentaDTO>> getVentasDelDia(@PathVariable UUID negocioId) {
		List<VentaDTO> ventas = ventaService.obtenerVentasDelDia(negocioId);
		return ResponseEntity.ok(ventas);
	}

	// Obtener ventas entre fechas
	@GetMapping("/fechas/negocio/{negocioId}")
	public ResponseEntity<List<VentaDTO>> getVentasEntreFechas(@PathVariable UUID negocioId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
		List<VentaDTO> ventas = ventaRepository.findByNegocioIdAndFechaHoraBetween(negocioId, fechaInicio, fechaFin)
				.stream().map(ventaMapper::toDTO).collect(Collectors.toList());
		return ResponseEntity.ok(ventas);
	}
}