package com.negocios.dto;

import com.negocios.model.Rol.NombreRol;

public class RolDTO {
	private Long id;
	private NombreRol nombreRol;

	// Constructor vacío
	public RolDTO() {
	}

	// Constructor completo
	public RolDTO(Long id, NombreRol nombreRol) {
		this.id = id;
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
}