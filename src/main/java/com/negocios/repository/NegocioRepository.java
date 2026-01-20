package com.negocios.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.negocios.model.Negocio;

@Repository
public interface NegocioRepository extends JpaRepository<Negocio, UUID> {

	// Buscar negocio por CIF/NIF
	Optional<Negocio> findByCifNif(String cifNif);

	// Verificar si existe un negocio con ese CIF/NIF
	boolean existsByCifNif(String cifNif);
}