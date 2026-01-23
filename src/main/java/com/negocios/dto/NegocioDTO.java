package com.negocios.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class NegocioDTO {
	private UUID id;
	private String nombre;
	private String cifNif;
	private String direccion;
	private LocalDateTime fechaCreacion;

	// Constructor vacío
	public NegocioDTO() {
	}

	// Constructor completo
	public NegocioDTO(UUID id, String nombre, String cifNif, String direccion, LocalDateTime fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.cifNif = cifNif;
		this.direccion = direccion;
		this.fechaCreacion = fechaCreacion;
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
}