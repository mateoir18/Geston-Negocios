package com.negocios.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class DetalleVentaDTO {
	private UUID id;
	private Integer cantidad;
	private BigDecimal precioUnitarioHistorico;
	private BigDecimal subtotal;
	private UUID productoId;
	private String productoNombre;
	private String productoSku;

	// Constructor vacío
	public DetalleVentaDTO() {
	}

	// Constructor completo
	public DetalleVentaDTO(UUID id, Integer cantidad, BigDecimal precioUnitarioHistorico, BigDecimal subtotal,
			UUID productoId, String productoNombre, String productoSku) {
		this.id = id;
		this.cantidad = cantidad;
		this.precioUnitarioHistorico = precioUnitarioHistorico;
		this.subtotal = subtotal;
		this.productoId = productoId;
		this.productoNombre = productoNombre;
		this.productoSku = productoSku;
	}

	// Getters y Setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitarioHistorico() {
		return precioUnitarioHistorico;
	}

	public void setPrecioUnitarioHistorico(BigDecimal precioUnitarioHistorico) {
		this.precioUnitarioHistorico = precioUnitarioHistorico;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public UUID getProductoId() {
		return productoId;
	}

	public void setProductoId(UUID productoId) {
		this.productoId = productoId;
	}

	public String getProductoNombre() {
		return productoNombre;
	}

	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}

	public String getProductoSku() {
		return productoSku;
	}

	public void setProductoSku(String productoSku) {
		this.productoSku = productoSku;
	}
}