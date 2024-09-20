package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.PadreDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Padre;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;

public class DtoMapperPadre {
    private Padre padre;

    private DtoMapperPadre() {
    }

    public static DtoMapperPadre builder() {
        return new DtoMapperPadre();
    }

    public DtoMapperPadre setPadre(Padre padre) {
        this.padre = padre;
        return this;
    }

    public PadreDto build() {
        if (padre == null) {
            throw new RuntimeException("Debe pasar el entity!");
        }
    
        // Convertir la lista de Estudiantes a un conjunto de IDs de Estudiantes
        Set<Long> estudiantesIds = padre.getEstudiantes() != null
                ? padre.getEstudiantes()
                        .stream()
                        .map(Estudiante::getId_estudiante)
                        .collect(Collectors.toSet())
                : Collections.emptySet();  // Si no hay estudiantes, devolver un conjunto vacío
    
        return new PadreDto(
                padre.getId_padre(),
                padre.getNombre(),
                padre.getEmail(),
                padre.getContraseña(),
                estudiantesIds
        );
    }
    

    // Método para convertir de PadreDto a Padre (si lo necesitas)
    public Padre toEntity(PadreDto padreDto, Set<Estudiante> estudiantes) {
        Padre padre = new Padre();
        padre.setId_padre(padreDto.getId_padre());
        padre.setNombre(padreDto.getNombre());
        padre.setEmail(padreDto.getEmail());
        padre.setContraseña(padreDto.getContraseña());
        padre.setEstudiantes(estudiantes); // Aquí necesitas setear las entidades Estudiante
        return padre;
    }
}
