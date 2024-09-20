package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.util.List;
import java.util.Optional;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.ProductoDto;

public interface ProductoService {

	List<ProductoDto> findAll();

	Optional<ProductoDto> findById(Long id);

	ProductoDto save(ProductoDto estudiante);

	Optional<ProductoDto> update(ProductoDto estudiante, Long id);

	void remove(Long id);
}
