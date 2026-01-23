package com.negocios.dto;

import java.util.UUID;

public class ClienteDTO {
	private UUID id;
	private String nombre;
	private String telefono;
	private String email;
	private UUID negocioId;
	private String negocioNombre;

	// Constructor vacío
	public ClienteDTO() {
	}

	// Constructor completo
	public ClienteDTO(UUID id, String nombre, String telefono, String email, UUID negocioId, String negocioNombre) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
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