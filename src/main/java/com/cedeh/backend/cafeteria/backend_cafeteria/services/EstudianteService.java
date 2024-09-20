package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.EstudianteDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;

public interface EstudianteService {

    List<EstudianteDto> findAll();

    Optional<EstudianteDto> findById(Long id);

    EstudianteDto save(EstudianteDto estudiante);

    Optional<EstudianteDto> update(EstudianteDto estudiante, Long id);

    void remove(Long id);

    Set<Estudiante> obtenerEstudiantesPorIds(Set<Long> estudiantesIds);

    // Nuevo método para obtener estudiantes sin padre
    List<EstudianteDto> findEstudiantesSinPadre();
}
