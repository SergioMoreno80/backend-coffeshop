package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.PadreDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Padre;

public interface PadreService {
    
    // Obtener todos los padres en formato de lista
    List<PadreDto> findAll();

    // Obtener todos los padres en formato de página
    Page<PadreDto> findAll(Pageable pageable);

    // Encontrar padre por ID
    Optional<PadreDto> findById(Long id);

    // Guardar nuevo padre
    PadreDto save(Padre padre);

    // Actualizar un padre existente
    Optional<PadreDto> update(PadreDto padre, Long id);

    // Asignar estudiante a un padre
    Padre asignarEstudiante(Long id_padre, Long id_estudiante);

    // Eliminar un padre por ID
    void remove(Long id);
}
