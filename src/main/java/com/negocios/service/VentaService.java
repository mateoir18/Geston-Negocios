package com.negocios.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.negocios.dto.VentaDTO;
import com.negocios.mapper.VentaMapper;
import com.negocios.model.DetalleVenta;
import com.negocios.model.Venta;
import com.negocios.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private VentaMapper ventaMapper;

	@Transactional
	public VentaDTO crearVenta(Venta venta) {
		// Asignar la venta a cada detalle antes de guardar
		if (venta.getDetalles() != null && !venta.getDetalles().isEmpty()) {
			for (DetalleVenta detalle : venta.getDetalles()) {
				detalle.setVenta(venta);
			}
		}

		// Guardar la venta (en cascada guardará los detalles)
		Venta ventaGuardada = ventaRepository.save(venta);

		// Convertir a DTO y retornar
		return ventaMapper.toDTO(ventaGuardada);
	}

	public List<VentaDTO> obtenerTodasLasVentas() {
		return ventaRepository.findAll().stream().map(ventaMapper::toDTO).collect(Collectors.toList());
	}

	public VentaDTO obtenerVentaPorId(UUID id) {
		return ventaRepository.findById(id).map(ventaMapper::toDTO).orElse(null);
	}

	public List<VentaDTO> obtenerVentasPorNegocio(UUID negocioId) {
		return ventaRepository.findByNegocioId(negocioId).stream().map(ventaMapper::toDTO).collect(Collectors.toList());
	}

	public List<VentaDTO> obtenerVentasDelDia(UUID negocioId) {
		return ventaRepository.findVentasDelDia(negocioId).stream().map(ventaMapper::toDTO)
				.collect(Collectors.toList());
	}
}