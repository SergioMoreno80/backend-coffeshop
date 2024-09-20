package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.ProductoDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Producto;

public class DtoMapperProductos {
private Producto producto;

	private DtoMapperProductos() {
	}

	public static DtoMapperProductos builder() {
		return new DtoMapperProductos();
	}

	public DtoMapperProductos setProducto(Producto producto) {
		this.producto = producto;
		return this;
	}

	public ProductoDto build() {
		if (producto == null) {
			throw new RuntimeException("Debe pasar el entity !");
		}

		return new ProductoDto(this.producto.getId_producto(), producto.getNombre(),
				producto.getDescripcion(), producto.getPrecio(), producto.getCategoria(),
				producto.getImagenDireccion(), null);

	}

}
