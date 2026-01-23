package com.negocios.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.negocios.dto.UsuarioDTO;
import com.negocios.model.Usuario;

@Component
public class UsuarioMapper {

	@Autowired
	private NegocioMapper negocioMapper;

	@Autowired
	private RolMapper rolMapper;

	public UsuarioDTO toDTO(Usuario usuario) {
		if (usuario == null) {
			return null;
		}

		return new UsuarioDTO(usuario.getId(), usuario.getEmail(), usuario.getNombreCompleto(),
				negocioMapper.toDTO(usuario.getNegocio()), rolMapper.toDTO(usuario.getRol()));
	}

	public Usuario toEntity(UsuarioDTO dto) {
		if (dto == null) {
			return null;
		}

		Usuario usuario = new Usuario();
		usuario.setId(dto.getId());
		usuario.setEmail(dto.getEmail());
		usuario.setNombreCompleto(dto.getNombreCompleto());

		return usuario;
	}
}