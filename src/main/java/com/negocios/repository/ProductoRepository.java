package com.negocios.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.negocios.model.Negocio;
import com.negocios.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {

	// Buscar todos los productos de un negocio
	List<Producto> findByNegocio(Negocio negocio);

	// Buscar productos por negocio ID
	List<Producto> findByNegocioId(UUID negocioId);

	// Buscar producto por SKU dentro de un negocio
	Optional<Producto> findBySkuAndNegocioId(String sku, UUID negocioId);

	// Buscar productos por nombre (búsqueda parcial)
	List<Producto> findByNombreContainingIgnoreCaseAndNegocioId(String nombre, UUID negocioId);

	// Buscar productos con stock bajo el mínimo
	@Query("SELECT p FROM Producto p WHERE p.stockActual <= p.stockMinimo AND p.negocio.id = ?1")
	List<Producto> findProductosConStockBajo(UUID negocioId);
}