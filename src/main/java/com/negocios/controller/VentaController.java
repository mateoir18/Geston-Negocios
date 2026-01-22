package com.negocios.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	// Obtener todas las ventas
	@GetMapping
	public ResponseEntity<List<Venta>> getAllVentas() {
		List<Venta> ventas = ventaService.obtenerTodasLasVentas();
		return ResponseEntity.ok(ventas);
	}

	// Obtener venta por ID
	@GetMapping("/{id}")
	public ResponseEntity<Venta> getVentaById(@PathVariable UUID id) {
		Venta venta = ventaService.obtenerVentaPorId(id);
		if (venta != null) {
			return ResponseEntity.ok(venta);
		}
		return ResponseEntity.notFound().build();
	}

	// Obtener ventas por negocio
	@GetMapping("/negocio/{negocioId}")
	public ResponseEntity<List<Venta>> getVentasByNegocio(@PathVariable UUID negocioId) {
		List<Venta> ventas = ventaService.obtenerVentasPorNegocio(negocioId);
		return ResponseEntity.ok(ventas);
	}

	// Obtener ventas por cliente
	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<List<Venta>> getVentasByCliente(@PathVariable UUID clienteId) {
		List<Venta> ventas = ventaRepository.findByClienteId(clienteId);
		return ResponseEntity.ok(ventas);
	}

	// Obtener ventas por usuario (empleado)
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Venta>> getVentasByUsuario(@PathVariable UUID usuarioId) {
		List<Venta> ventas = ventaRepository.findByUsuarioId(usuarioId);
		return ResponseEntity.ok(ventas);
	}

	// Crear una nueva venta (USANDO EL SERVICE)
	@PostMapping
	public ResponseEntity<Venta> createVenta(@Valid @RequestBody Venta venta) {
		Venta nuevaVenta = ventaService.crearVenta(venta);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
	}

	// Actualizar una venta
	@PutMapping("/{id}")
	public ResponseEntity<Venta> updateVenta(@PathVariable UUID id, @Valid @RequestBody Venta ventaActualizada) {
		return ventaRepository.findById(id).map(venta -> {
			venta.setTotalVenta(ventaActualizada.getTotalVenta());
			venta.setCliente(ventaActualizada.getCliente());
			Venta actualizada = ventaRepository.save(venta);
			return ResponseEntity.ok(actualizada);
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
	public ResponseEntity<List<Venta>> getVentasDelDia(@PathVariable UUID negocioId) {
		List<Venta> ventas = ventaService.obtenerVentasDelDia(negocioId);
		return ResponseEntity.ok(ventas);
	}

	// Obtener ventas entre fechas
	@GetMapping("/fechas/negocio/{negocioId}")
	public ResponseEntity<List<Venta>> getVentasEntreFechas(@PathVariable UUID negocioId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
		List<Venta> ventas = ventaRepository.findByNegocioIdAndFechaHoraBetween(negocioId, fechaInicio, fechaFin);
		return ResponseEntity.ok(ventas);
	}
}