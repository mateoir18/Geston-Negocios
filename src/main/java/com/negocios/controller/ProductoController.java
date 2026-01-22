package com.negocios.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.negocios.model.Producto;
import com.negocios.repository.ProductoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	// Obtener todos los productos
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos() {
		List<Producto> productos = productoRepository.findAll();
		return ResponseEntity.ok(productos);
	}

	// Obtener producto por ID
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable UUID id) {
		return productoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Obtener productos por negocio
	@GetMapping("/negocio/{negocioId}")
	public ResponseEntity<List<Producto>> getProductosByNegocio(@PathVariable UUID negocioId) {
		List<Producto> productos = productoRepository.findByNegocioId(negocioId);
		return ResponseEntity.ok(productos);
	}

	// Crear un nuevo producto
	@PostMapping
	public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto) {
		Producto nuevoProducto = productoRepository.save(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
	}

	// Actualizar un producto
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable UUID id,
			@Valid @RequestBody Producto productoActualizado) {
		return productoRepository.findById(id).map(producto -> {
			producto.setNombre(productoActualizado.getNombre());
			producto.setSku(productoActualizado.getSku());
			producto.setPrecioVenta(productoActualizado.getPrecioVenta());
			producto.setStockActual(productoActualizado.getStockActual());
			producto.setStockMinimo(productoActualizado.getStockMinimo());
			Producto actualizado = productoRepository.save(producto);
			return ResponseEntity.ok(actualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Eliminar un producto
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProducto(@PathVariable UUID id) {
		return productoRepository.findById(id).map(producto -> {
			productoRepository.delete(producto);
			return ResponseEntity.ok().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}

	// Buscar productos con stock bajo
	@GetMapping("/stock-bajo/{negocioId}")
	public ResponseEntity<List<Producto>> getProductosConStockBajo(@PathVariable UUID negocioId) {
		List<Producto> productos = productoRepository.findProductosConStockBajo(negocioId);
		return ResponseEntity.ok(productos);
	}

	// Buscar producto por SKU
	@GetMapping("/sku/{sku}/negocio/{negocioId}")
	public ResponseEntity<Producto> getProductoBySku(@PathVariable String sku, @PathVariable UUID negocioId) {
		return productoRepository.findBySkuAndNegocioId(sku, negocioId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}