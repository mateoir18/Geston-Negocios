package com.negocios.mapper;

import org.springframework.stereotype.Component;

import com.negocios.dto.NegocioDTO;
import com.negocios.model.Negocio;

@Component
public class NegocioMapper {

	public NegocioDTO toDTO(Negocio negocio) {
		if (negocio == null) {
			return null;
		}

		return new NegocioDTO(negocio.getId(), negocio.getNombre(), negocio.getCifNif(), negocio.getDireccion(),
				negocio.getFechaCreacion());
	}

	public Negocio toEntity(NegocioDTO dto) {
		if (dto == null) {
			return null;
		}

		Negocio negocio = new Negocio();
		negocio.setId(dto.getId());
		negocio.setNombre(dto.getNombre());
		negocio.setCifNif(dto.getCifNif());
		negocio.setDireccion(dto.getDireccion());

		return negocio;
	}
}