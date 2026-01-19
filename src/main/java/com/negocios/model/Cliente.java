package com.negocios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotBlank(message = "El nombre del cliente es obligatorio")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String nombre;

	@Size(max = 20)
	@Column(length = 20)
	private String telefono;

	@Email(message = "El email debe ser válido")
	@Size(max = 100)
	@Column(length = 100)
	private String email;

	// Relación Many-to-One con Negocio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "negocio_id", nullable = false)
	private Negocio negocio;

	// Relación One-to-Many con Venta
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Venta> ventas = new ArrayList<>();

	// Constructor vacío (requerido por JPA)
	public Cliente() {
	}

	// Constructor con parámetros
	public Cliente(String nombre, String telefono, String email, Negocio negocio) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
}