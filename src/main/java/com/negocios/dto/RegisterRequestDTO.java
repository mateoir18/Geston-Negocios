package com.negocios.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email debe ser válido")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password;

	@NotBlank(message = "El nombre completo es obligatorio")
	private String nombreCompleto;

	@NotNull(message = "El ID del negocio es obligatorio")
	private UUID negocioId;

	@NotNull(message = "El ID del rol es obligatorio")
	private Long rolId;

	// Constructor vacío
	public RegisterRequestDTO() {
	}

	// Constructor con parámetros
	public RegisterRequestDTO(String email, String password, String nombreCompleto, UUID negocioId, Long rolId) {
		this.email = email;
		this.password = password;
		this.nombreCompleto = nombreCompleto;
		this.negocioId = negocioId;
		this.rolId = rolId;
	}

	// Getters y Setters
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

	public UUID getNegocioId() {
		return negocioId;
	}

	public void setNegocioId(UUID negocioId) {
		this.negocioId = negocioId;
	}

	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
}