package com.negocios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.negocios.model.Rol;
import com.negocios.model.Rol.NombreRol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

	// Buscar rol por nombre
	Optional<Rol> findByNombreRol(NombreRol nombreRol);
}