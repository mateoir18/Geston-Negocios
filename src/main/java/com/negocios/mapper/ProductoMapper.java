package com.negocios.mapper;

import org.springframework.stereotype.Component;

import com.negocios.dto.ProductoDTO;
import com.negocios.model.Producto;

@Component
public class ProductoMapper {

	public ProductoDTO toDTO(Producto producto) {
		if (producto == null) {
			return null;
		}

		return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getSku(), producto.getPrecioVenta(),
				producto.getStockActual(), producto.getStockMinimo(), producto.getNegocio().getId(),
				producto.getNegocio().getNombre());
	}

	public Producto toEntity(ProductoDTO dto) {
		if (dto == null) {
			return null;
		}

		Producto producto = new Producto();
		producto.setId(dto.getId());
		producto.setNombre(dto.getNombre());
		producto.setSku(dto.getSku());
		producto.setPrecioVenta(dto.getPrecioVenta());
		producto.setStockActual(dto.getStockActual());
		producto.setStockMinimo(dto.getStockMinimo());

		return producto;
	}
}