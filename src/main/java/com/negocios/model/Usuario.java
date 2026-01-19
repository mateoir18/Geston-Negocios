package com.negocios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email debe ser válido")
	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 60, max = 60) // BCrypt genera hashes de 60 caracteres
	@Column(name = "password_hash", nullable = false, length = 60)
	private String passwordHash;

	@NotBlank(message = "El nombre completo es obligatorio")
	@Size(max = 100)
	@Column(name = "nombre_completo", nullable = false, length = 100)
	private String nombreCompleto;

	// Relación Many-to-One con Negocio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "negocio_id", nullable = false)
	private Negocio negocio;

	// Relación Many-to-One con Rol
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rol_id", nullable = false)
	private Rol rol;

	// Relación One-to-Many con Venta (ventas realizadas por este usuario)
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Venta> ventas = new ArrayList<>();

	// Constructor vacío (requerido por JPA)
	public Usuario() {
	}

	// Constructor con parámetros
	public Usuario(String email, String passwordHash, String nombreCompleto, Negocio negocio, Rol rol) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.nombreCompleto = nombreCompleto;
		this.negocio = negocio;
		this.rol = rol;
	}

	// Getters y Setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
}