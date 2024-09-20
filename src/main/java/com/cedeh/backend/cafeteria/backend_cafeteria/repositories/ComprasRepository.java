package com.cedeh.backend.cafeteria.backend_cafeteria.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Compras;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;

public interface ComprasRepository extends CrudRepository<Compras, Long>{

        List<Compras> findByEstudianteAndFechaBetween(Estudiante estudiante, LocalDateTime fechaInicio, LocalDateTime fechaFin);
        List<Compras> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
