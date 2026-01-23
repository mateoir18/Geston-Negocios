package com.negocios.mapper;

import org.springframework.stereotype.Component;

import com.negocios.dto.RolDTO;
import com.negocios.model.Rol;

@Component
public class RolMapper {

	public RolDTO toDTO(Rol rol) {
		if (rol == null) {
			return null;
		}

		return new RolDTO(rol.getId(), rol.getNombreRol());
	}

	public Rol toEntity(RolDTO dto) {
		if (dto == null) {
			return null;
		}

		Rol rol = new Rol();
		rol.setId(dto.getId());
		rol.setNombreRol(dto.getNombreRol());

		return rol;
	}
}