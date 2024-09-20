package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.ComprasDto;

public interface ComprasService {

    List<ComprasDto> findAll();

    Optional<ComprasDto> findById(Long id);

    ComprasDto save(ComprasDto compras);

    Optional<ComprasDto> update(ComprasDto compras, Long id);

    void remove(Long id);

    List<ComprasDto> findComprasByEstudianteAndFecha(Long id_estudiante, LocalDateTime fechaInicio,
            LocalDateTime fechaFin);

    List<ComprasDto> findComprasByFecha(LocalDateTime fechaInicio,
            LocalDateTime fechaFin);

}
