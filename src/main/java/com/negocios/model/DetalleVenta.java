package com.negocios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "detalles_venta")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Min(value = 1, message = "La cantidad debe ser al menos 1")
	@Column(nullable = false)
	private Integer cantidad;

	@DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor que 0")
	@Column(name = "precio_unitario_historico", nullable = false, precision = 10, scale = 2)
	private BigDecimal precioUnitarioHistorico;

	// Relación Many-to-One con Venta
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venta_id", nullable = false)
	private Venta venta;

	// Relación Many-to-One con Producto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;

	// Constructor vacío (requerido por JPA)
	public DetalleVenta() {
	}

	// Constructor con parámetros
	public DetalleVenta(Integer cantidad, BigDecimal precioUnitarioHistorico, Venta venta, Producto producto) {
		this.cantidad = cantidad;
		this.precioUnitarioHistorico = precioUnitarioHistorico;
		this.venta = venta;
		this.producto = producto;
	}

	// Método calculado para obtener el subtotal
	public BigDecimal getSubtotal() {
		return precioUnitarioHistorico.multiply(new BigDecimal(cantidad));
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

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}