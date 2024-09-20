package com.cedeh.backend.cafeteria.backend_cafeteria.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Recargas;
@Repository
public interface RecargaRepository extends CrudRepository<Recargas, Long> {

    List<Recargas> findByEstudiante(Estudiante estudiante);

}
