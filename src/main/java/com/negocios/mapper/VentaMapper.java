package com.negocios.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.negocios.dto.DetalleVentaDTO;
import com.negocios.dto.VentaDTO;
import com.negocios.model.DetalleVenta;
import com.negocios.model.Venta;

@Component
public class VentaMapper {

	public VentaDTO toDTO(Venta venta) {
		if (venta == null) {
			return null;
		}

		List<DetalleVentaDTO> detallesDTO = new ArrayList<>();
		if (venta.getDetalles() != null) {
			detallesDTO = venta.getDetalles().stream().map(this::detalleToDTO).collect(Collectors.toList());
		}

		return new VentaDTO(venta.getId(), venta.getFechaHora(), venta.getTotalVenta(),
				venta.getCliente() != null ? venta.getCliente().getId() : null,
				venta.getCliente() != null ? venta.getCliente().getNombre() : null, venta.getUsuario().getId(),
				venta.getUsuario().getNombreCompleto(), venta.getNegocio().getId(), venta.getNegocio().getNombre(),
				detallesDTO);
	}

	public DetalleVentaDTO detalleToDTO(DetalleVenta detalle) {
		if (detalle == null) {
			return null;
		}

		return new DetalleVentaDTO(detalle.getId(), detalle.getCantidad(), detalle.getPrecioUnitarioHistorico(),
				detalle.getSubtotal(), detalle.getProducto().getId(), detalle.getProducto().getNombre(),
				detalle.getProducto().getSku());
	}
}