package com.negocios.dto;

import java.util.UUID;

public class UsuarioDTO {
	private UUID id;
	private String email;
	private String nombreCompleto;
	private NegocioDTO negocio;
	private RolDTO rol;

	// Constructor vacío
	public UsuarioDTO() {
	}

	// Constructor completo
	public UsuarioDTO(UUID id, String email, String nombreCompleto, NegocioDTO negocio, RolDTO rol) {
		this.id = id;
		this.email = email;
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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public NegocioDTO getNegocio() {
		return negocio;
	}

	public void setNegocio(NegocioDTO negocio) {
		this.negocio = negocio;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}
}