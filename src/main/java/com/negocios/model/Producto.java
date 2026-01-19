package com.negocios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotBlank(message = "El nombre del producto es obligatorio")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String nombre;

	@Size(max = 50)
	@Column(unique = true, length = 50)
	private String sku;

	@DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
	@Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
	private BigDecimal precioVenta;

	@Min(value = 0, message = "El stock actual no puede ser negativo")
	@Column(name = "stock_actual", nullable = false)
	private Integer stockActual;

	@Min(value = 0, message = "El stock mínimo no puede ser negativo")
	@Column(name = "stock_minimo", nullable = false)
	private Integer stockMinimo;

	// Relación Many-to-One con Negocio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "negocio_id", nullable = false)
	private Negocio negocio;

	// Relación One-to-Many con DetalleVenta
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<DetalleVenta> detallesVenta = new ArrayList<>();

	// Constructor vacío (requerido por JPA)
	public Producto() {
	}

	// Constructor con parámetros
	public Producto(String nombre, String sku, BigDecimal precioVenta, Integer stockActual, Integer stockMinimo,
			Negocio negocio) {
		this.nombre = nombre;
		this.sku = sku;
		this.precioVenta = precioVenta;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.negocio = negocio;
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

	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}

	public List<DetalleVenta> getDetallesVenta() {
		return detallesVenta;
	}

	public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
		this.detallesVenta = detallesVenta;
	}
}