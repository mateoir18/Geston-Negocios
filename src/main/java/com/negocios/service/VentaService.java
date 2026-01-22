package com.negocios.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.negocios.model.DetalleVenta;
import com.negocios.model.Venta;
import com.negocios.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;

	@Transactional
	public Venta crearVenta(Venta venta) {
		// Asignar la venta a cada detalle antes de guardar
		if (venta.getDetalles() != null && !venta.getDetalles().isEmpty()) {
			for (DetalleVenta detalle : venta.getDetalles()) {
				detalle.setVenta(venta);
			}
		}

		// Guardar la venta (en cascada guardará los detalles)
		return ventaRepository.save(venta);
	}

	public List<Venta> obtenerTodasLasVentas() {
		return ventaRepository.findAll();
	}

	public Venta obtenerVentaPorId(UUID id) {
		return ventaRepository.findById(id).orElse(null);
	}

	public List<Venta> obtenerVentasPorNegocio(UUID negocioId) {
		return ventaRepository.findByNegocioId(negocioId);
	}

	public List<Venta> obtenerVentasDelDia(UUID negocioId) {
		return ventaRepository.findVentasDelDia(negocioId);
	}
}