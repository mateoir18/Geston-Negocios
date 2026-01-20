package com.negocios.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.negocios.model.Cliente;
import com.negocios.model.Negocio;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	// Buscar todos los clientes de un negocio
	List<Cliente> findByNegocio(Negocio negocio);

	// Buscar clientes por negocio ID
	List<Cliente> findByNegocioId(UUID negocioId);

	// Buscar clientes por nombre (búsqueda parcial)
	List<Cliente> findByNombreContainingIgnoreCaseAndNegocioId(String nombre, UUID negocioId);

	// Buscar cliente por email dentro de un negocio
	List<Cliente> findByEmailAndNegocioId(String email, UUID negocioId);
}