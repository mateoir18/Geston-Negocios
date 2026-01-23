package com.negocios.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VentaDTO {
	private UUID id;
	private LocalDateTime fechaHora;
	private BigDecimal totalVenta;
	private UUID clienteId;
	private String clienteNombre;
	private UUID usuarioId;
	private String usuarioNombre;
	private UUID negocioId;
	private String negocioNombre;
	private List<DetalleVentaDTO> detalles = new ArrayList<>();

	// Constructor vacío
	public VentaDTO() {
	}

	// Constructor completo
	public VentaDTO(UUID id, LocalDateTime fechaHora, BigDecimal totalVenta, UUID clienteId, String clienteNombre,
			UUID usuarioId, String usuarioNombre, UUID negocioId, String negocioNombre,
			List<DetalleVentaDTO> detalles) {
		this.id = id;
		this.fechaHora = fechaHora;
		this.totalVenta = totalVenta;
		this.clienteId = clienteId;
		this.clienteNombre = clienteNombre;
		this.usuarioId = usuarioId;
		this.usuarioNombre = usuarioNombre;
		this.negocioId = negocioId;
		this.negocioNombre = negocioNombre;
		this.detalles = detalles;
	}

	// Getters y Setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public BigDecimal getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(BigDecimal totalVenta) {
		this.totalVenta = totalVenta;
	}

	public UUID getClienteId() {
		return clienteId;
	}

	public void setClienteId(UUID clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public UUID getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UUID usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	public UUID getNegocioId() {
		return negocioId;
	}

	public void setNegocioId(UUID negocioId) {
		this.negocioId = negocioId;
	}

	public String getNegocioNombre() {
		return negocioNombre;
	}

	public void setNegocioNombre(String negocioNombre) {
		this.negocioNombre = negocioNombre;
	}

	public List<DetalleVentaDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleVentaDTO> detalles) {
		this.detalles = detalles;
	}
}