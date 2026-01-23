package com.negocios.mapper;

import org.springframework.stereotype.Component;

import com.negocios.dto.ClienteDTO;
import com.negocios.model.Cliente;

@Component
public class ClienteMapper {

	public ClienteDTO toDTO(Cliente cliente) {
		if (cliente == null) {
			return null;
		}

		return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getTelefono(), cliente.getEmail(),
				cliente.getNegocio().getId(), cliente.getNegocio().getNombre());
	}

	public Cliente toEntity(ClienteDTO dto) {
		if (dto == null) {
			return null;
		}

		Cliente cliente = new Cliente();
		cliente.setId(dto.getId());
		cliente.setNombre(dto.getNombre());
		cliente.setTelefono(dto.getTelefono());
		cliente.setEmail(dto.getEmail());

		return cliente;
	}
}