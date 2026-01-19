package com.negocios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "fecha_hora", nullable = false)
	private LocalDateTime fechaHora;

	@DecimalMin(value = "0.0", inclusive = true, message = "El total de la venta no puede ser negativo")
	@Column(name = "total_venta", nullable = false, precision = 10, scale = 2)
	private BigDecimal totalVenta;

	// Relación Many-to-One con Cliente (puede ser null para ventas sin cliente)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	// Relación Many-to-One con Usuario (empleado que realizó la venta)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	// Relación Many-to-One con Negocio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "negocio_id", nullable = false)
	private Negocio negocio;

	// Relación One-to-Many con DetalleVenta
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleVenta> detalles = new ArrayList<>();

	@PrePersist
	protected void onCreate() {
		fechaHora = LocalDateTime.now();
	}

	// Constructor vacío (requerido por JPA)
	public Venta() {
	}

	// Constructor con parámetros
	public Venta(BigDecimal totalVenta, Cliente cliente, Usuario usuario, Negocio negocio) {
		this.totalVenta = totalVenta;
		this.cliente = cliente;
		this.usuario = usuario;
		this.negocio = negocio;
	}

	// Método auxiliar para añadir detalles
	public void addDetalle(DetalleVenta detalle) {
		detalles.add(detalle);
		detalle.setVenta(this);
	}

	// Método auxiliar para remover detalles
	public void removeDetalle(DetalleVenta detalle) {
		detalles.remove(detalle);
		detalle.setVenta(null);
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}

	public List<DetalleVenta> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleVenta> detalles) {
		this.detalles = detalles;
	}
}