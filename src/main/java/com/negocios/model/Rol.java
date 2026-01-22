package com.negocios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "El nombre del rol es obligatorio")
	@Column(name = "nombre_rol", nullable = false, unique = true, length = 50)
	@Enumerated(EnumType.STRING)
	private NombreRol nombreRol;

	// Enum para los tipos de rol
	public enum NombreRol {
		ADMIN, EMPLEADO
	}

	// Constructor vacío (requerido por JPA)
	public Rol() {
	}

	// Constructor con parámetros
	public Rol(NombreRol nombreRol) {
		this.nombreRol = nombreRol;
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NombreRol getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(NombreRol nombreRol) {
		this.nombreRol = nombreRol;
	}

	@Override
	public String toString() {
		return "Rol{" + "id=" + id + ", nombreRol=" + nombreRol + '}';
	}
}