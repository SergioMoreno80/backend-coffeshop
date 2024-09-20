package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.EstudianteDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper.DtoMapperEstudiante;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.EstudianteRepository;

import jakarta.transaction.Transactional;

@Service
public class EstudianteServiceImp implements EstudianteService {

    @Autowired
    private EstudianteRepository repository;


    // Método para obtener estudiantes sin padre
    @Transactional
    public List<EstudianteDto> findEstudiantesSinPadre() {
        List<Estudiante> estudiantesSinPadre = repository.findEstudiantesSinPadre();
        return estudiantesSinPadre.stream()
            .map(e -> DtoMapperEstudiante.builder().setEstudiante(e).build())
            .collect(Collectors.toList());
    }
    
    @Override
    public List<EstudianteDto> findAll() {
        List<Estudiante> estudiante = (List<Estudiante>) repository.findAll();
        return estudiante.stream().map(u -> DtoMapperEstudiante.builder().setEstudiante(u).build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EstudianteDto> findById(Long id) {
        return repository.findById(id).map(u -> DtoMapperEstudiante.builder().setEstudiante(u).build());
    }

    @Override
    @Transactional
    public EstudianteDto save(EstudianteDto Estudiante) {
        Estudiante newDb = new Estudiante();
        newDb.setNombre(Estudiante.getNombre());
        newDb.setApellido(Estudiante.getApellido());
        newDb.setSaldo(Estudiante.getSaldo());
        newDb.setNumeroIdentificacion(Estudiante.getNumeroIdentificacion());
        newDb.setGradoEstudios(Estudiante.getGradoEstudios());
        return DtoMapperEstudiante.builder().setEstudiante(repository.save(newDb)).build();
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<EstudianteDto> update(EstudianteDto Estudiante, Long id) {
        Optional<Estudiante> o = repository.findById(id);
        Estudiante fabOptional = null;

        if (o.isPresent()) {
            Estudiante newDb = o.orElseThrow();
            newDb.setNombre(Estudiante.getNombre());
            newDb.setApellido(Estudiante.getApellido());
            newDb.setSaldo(Estudiante.getSaldo());
            newDb.setNumeroIdentificacion(Estudiante.getNumeroIdentificacion());
            newDb.setGradoEstudios(Estudiante.getGradoEstudios());
            fabOptional = repository.save(newDb);
        }
        return Optional.ofNullable(DtoMapperEstudiante.builder().setEstudiante(fabOptional).build());
    }

    public Set<Estudiante> obtenerEstudiantesPorIds(Set<Long> estudiantesIds) {
        return estudiantesIds.stream()
            .map(id -> repository.findById(id)
                .orElseThrow())
            .collect(Collectors.toSet());
    }
}
