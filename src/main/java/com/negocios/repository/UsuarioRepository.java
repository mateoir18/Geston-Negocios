package com.negocios.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.negocios.model.Negocio;
import com.negocios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	// Buscar usuario por email
	Optional<Usuario> findByEmail(String email);

	// Verificar si existe un usuario con ese email
	boolean existsByEmail(String email);

	// Buscar todos los usuarios de un negocio
	List<Usuario> findByNegocio(Negocio negocio);

	// Buscar usuarios por negocio ID
	List<Usuario> findByNegocioId(UUID negocioId);
}