package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.util.List;
import java.util.Optional;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Detalle;

public interface DetalleService {
 List<Detalle> findAll();

    Optional<Detalle> findById(Long id);

    Detalle save(Detalle detalle);

    Optional<Detalle> update(Detalle detalle, Long id);

    void remove(Long id);
}
