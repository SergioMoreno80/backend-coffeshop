package com.cedeh.backend.cafeteria.backend_cafeteria.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
