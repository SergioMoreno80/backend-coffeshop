package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.util.List;
import java.util.Optional;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.RecargasDto;
public interface RecargaService {

    List<RecargasDto> findAll();
    Optional<RecargasDto> findById(Long id);
    RecargasDto save(RecargasDto recarga);
    Optional<RecargasDto> update(RecargasDto recarga, Long id);
    void remove (Long id);
    List<RecargasDto> findRecargasWithSaldo(Long id_estudiante);
    
}
