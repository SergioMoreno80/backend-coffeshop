package com.cedeh.backend.cafeteria.backend_cafeteria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;

public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {

// Método para obtener estudiantes sin padre
@Query("SELECT e FROM Estudiante e WHERE e.padre IS NULL")
List<Estudiante> findEstudiantesSinPadre();


}
