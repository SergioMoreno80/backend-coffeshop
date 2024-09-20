package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.RecargasDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper.DtoMapperRecargas;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Recargas;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.EstudianteRepository;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.RecargaRepository;

import jakarta.transaction.Transactional;

@Service
public class RecargaServiceImp implements RecargaService {

    @Autowired
    private RecargaRepository repository;
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<RecargasDto> findAll() {
        List<Recargas> recargas = (List<Recargas>) repository.findAll();
        return recargas.stream().map(u -> DtoMapperRecargas.builder().setRecargas(u).build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RecargasDto> findById(Long id) {
        return repository.findById(id).map(u -> DtoMapperRecargas.builder().setRecargas(u).build());
    }

    @Override
    @Transactional
    public RecargasDto save(RecargasDto recargas) {

        try {
            Recargas newDb = new Recargas();
            Optional<Estudiante> optionalEst = estudianteRepository.findById(Long.valueOf(recargas.getId_estudiante()));
            // Verificar si el estudiante existe
            if (!optionalEst.isPresent()) {
                throw new RuntimeException("Estudiante no encontrado");
            }

            Estudiante estudiante = optionalEst.get();

            newDb.setEstudiante(estudiante);
            newDb.setFecha(recargas.getFecha());
            newDb.setMonto(recargas.getMonto());
            newDb.setObservaciones(recargas.getObservaciones());

            // Guardar la recarga
            Recargas savedRecarga = repository.save(newDb);

            // Actualizar el saldo del estudiante
            estudiante.setSaldo(estudiante.getSaldo().add(recargas.getMonto()));
            estudianteRepository.save(estudiante);

            return DtoMapperRecargas.builder().setRecargas(repository.save(savedRecarga)).build();
        } catch (Exception e) {
            // Log the error
            throw new RuntimeException("Error al guardar la recarga", e);
        }

    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<RecargasDto> update(RecargasDto Recargas, Long id) {
        Optional<Recargas> o = repository.findById(id);
        Recargas fabOptional = null;

        if (o.isPresent()) {
            Recargas newDb = o.orElseThrow();
            Optional<Estudiante> optionalEst = estudianteRepository.findById(Long.valueOf(Recargas.getId_estudiante()));
            newDb.setEstudiante(optionalEst.get());
            newDb.setFecha(Recargas.getFecha());
            newDb.setMonto(Recargas.getMonto());
            newDb.setObservaciones(Recargas.getObservaciones());
            fabOptional = repository.save(newDb);
        }
        return Optional.ofNullable(DtoMapperRecargas.builder().setRecargas(fabOptional).build());
    }

    @Override
    public List<RecargasDto> findRecargasWithSaldo(Long id_estudiante) {
        Optional<Estudiante> optionalEst = estudianteRepository.findById(Long.valueOf(id_estudiante));
        Estudiante estudiante = optionalEst.get();
        List<Recargas> recargas = repository.findByEstudiante(estudiante);

        BigDecimal saldoActual = recargas.stream()
                .map(Recargas::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return recargas.stream()
                .map(recarga -> new RecargasDto(recarga.getId_recarga(), recarga.getEstudiante().getId_estudiante(),
                        recarga.getMonto(), recarga.getFecha(),
                        recarga.getObservaciones(), saldoActual))
                .collect(Collectors.toList());
                // return recargas.stream()
                // .map(recarga -> DtoMapperRecargas.builder().setRecargas(recarga).build())
                // .collect(Collectors.toList());
    }

}
