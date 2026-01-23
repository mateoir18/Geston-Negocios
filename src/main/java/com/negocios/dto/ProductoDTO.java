package com.negocios.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductoDTO {
	private UUID id;
	private String nombre;
	private String sku;
	private BigDecimal precioVenta;
	private Integer stockActual;
	private Integer stockMinimo;
	private UUID negocioId;
	private String negocioNombre;

	// Constructor vacío
	public ProductoDTO() {
	}

	// Constructor completo
	public ProductoDTO(UUID id, String nombre, String sku, BigDecimal precioVenta, Integer stockActual,
			Integer stockMinimo, UUID negocioId, String negocioNombre) {
		this.id = id;
		this.nombre = nombre;
		this.sku = sku;
		this.precioVenta = precioVenta;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.negocioId = negocioId;
		this.negocioNombre = negocioNombre;
	}

	// Getters y Setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getStockActual() {
		return stockActual;
	}

	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}

	public Integer getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
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
}