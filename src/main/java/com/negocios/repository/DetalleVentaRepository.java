package com.negocios.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.negocios.model.DetalleVenta;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, UUID> {

	// Buscar detalles de una venta específica
	List<DetalleVenta> findByVentaId(UUID ventaId);

	// Buscar detalles por producto
	List<DetalleVenta> findByProductoId(UUID productoId);

	// Obtener productos más vendidos de un negocio
	@Query("SELECT d FROM DetalleVenta d WHERE d.venta.negocio.id = ?1 ORDER BY d.cantidad DESC")
	List<DetalleVenta> findProductosMasVendidos(UUID negocioId);
}