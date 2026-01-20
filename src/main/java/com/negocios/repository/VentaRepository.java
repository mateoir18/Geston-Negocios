package com.negocios.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.negocios.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, UUID> {

	// Buscar todas las ventas de un negocio
	List<Venta> findByNegocioId(UUID negocioId);

	// Buscar ventas por cliente
	List<Venta> findByClienteId(UUID clienteId);

	// Buscar ventas por usuario (empleado que vendió)
	List<Venta> findByUsuarioId(UUID usuarioId);

	// Buscar ventas entre fechas
	List<Venta> findByNegocioIdAndFechaHoraBetween(UUID negocioId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	// Obtener ventas del día
	@Query("SELECT v FROM Venta v WHERE v.negocio.id = :negocioId AND CAST(v.fechaHora AS date) = CURRENT_DATE")
    List<Venta> findVentasDelDia(UUID negocioId);
}