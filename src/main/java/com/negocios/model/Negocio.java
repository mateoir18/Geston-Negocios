package com.negocios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "negocios")
public class Negocio {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotBlank(message = "El nombre del negocio es obligatorio")
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	private String nombre;

	@NotBlank(message = "El CIF/NIF es obligatorio")
	@Size(max = 20)
	@Column(name = "cif_nif", nullable = false, unique = true, length = 20)
	private String cifNif;

	@Size(max = 255)
	@Column(length = 255)
	private String direccion;

	@Column(name = "fecha_creacion", nullable = false, updatable = false)
	private LocalDateTime fechaCreacion;

	// Relación One-to-Many con Usuario
	@OneToMany(mappedBy = "negocio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Usuario> usuarios = new ArrayList<>();

	// Relación One-to-Many con Producto
	@OneToMany(mappedBy = "negocio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Producto> productos = new ArrayList<>();

	// Relación One-to-Many con Cliente
	@OneToMany(mappedBy = "negocio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cliente> clientes = new ArrayList<>();

	// Relación One-to-Many con Venta
	@OneToMany(mappedBy = "negocio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Venta> ventas = new ArrayList<>();

	@PrePersist
	protected void onCreate() {
		fechaCreacion = LocalDateTime.now();
	}

	// Constructor vacío (requerido por JPA)
	public Negocio() {
	}

	// Constructor con parámetros
	public Negocio(String nombre, String cifNif, String direccion) {
		this.nombre = nombre;
		this.cifNif = cifNif;
		this.direccion = direccion;
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

	public String getCifNif() {
		return cifNif;
	}

	public void setCifNif(String cifNif) {
		this.cifNif = cifNif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
}