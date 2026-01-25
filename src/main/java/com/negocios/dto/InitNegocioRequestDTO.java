package com.negocios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InitNegocioRequestDTO {

	// Datos del negocio
	@NotBlank(message = "El nombre del negocio es obligatorio")
	private String nombreNegocio;

	@NotBlank(message = "El CIF/NIF es obligatorio")
	private String cifNif;

	private String direccion;

	// Datos del admin
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email debe ser válido")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password;

	@NotBlank(message = "El nombre completo es obligatorio")
	private String nombreCompleto;

	// Constructor vacío
	public InitNegocioRequestDTO() {
	}

	// Constructor completo
	public InitNegocioRequestDTO(String nombreNegocio, String cifNif, String direccion, String email, String password,
			String nombreCompleto) {
		this.nombreNegocio = nombreNegocio;
		this.cifNif = cifNif;
		this.direccion = direccion;
		this.email = email;
		this.password = password;
		this.nombreCompleto = nombreCompleto;
	}

	// Getters y Setters
	public String getNombreNegocio() {
		return nombreNegocio;
	}

	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
}