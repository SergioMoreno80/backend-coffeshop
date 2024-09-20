package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.PadreDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper.DtoMapperPadre;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Padre;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.EstudianteRepository;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.PadreRepository;
import jakarta.transaction.Transactional;

@Service
public class PadreServiceImp implements PadreService {

    @Autowired
    private PadreRepository repository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<PadreDto> findAll() {
        // Convertir lista de entidades a lista de DTOs
        // Convertir Iterable a List
        List<Padre> padres = (List<Padre>) repository.findAll();
        return padres.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PadreDto> findAll(Pageable pageable) {
        // Convertir página de entidades a página de DTOs
        return repository.findAll(pageable)
                .map(this::convertToDto);
    }

    @Override
    public Optional<PadreDto> findById(Long id) {
        // Buscar padre por ID y convertirlo a DTO si existe
        return repository.findById(id)
                .map(this::convertToDto);
    }

    @Override
    @Transactional
    public PadreDto save(Padre padre) {

        // Guardar el objeto padre, lo que también guardará los estudiantes asociados
        Padre savedPadre = repository.save(padre);
        // recorrer estudiantes
        for (Estudiante estudiante : savedPadre.getEstudiantes()) {
            estudiante.setPadre(savedPadre); // Asegúrate de que cada estudiante tenga asignado el padre
        }
        savedPadre = repository.save(savedPadre);
        // Padre savedPadre = repository.save(padre);
        // return
        // DtoMapperPadre.builder().setPadre(repository.save(savedPadre)).build();
        return DtoMapperPadre.builder().setPadre(savedPadre).build();
    }

    @Override
    public Optional<PadreDto> update(PadreDto padreDto, Long id) {
        // Buscar el padre, actualizar la entidad y guardarla
        Optional<Padre> optionalPadre = repository.findById(id);
        if (optionalPadre.isPresent()) {
            Padre padre = optionalPadre.get();
            padre.setNombre(padreDto.getNombre());
            padre.setEmail(padreDto.getEmail());
            // Aquí puedes actualizar otros campos según sea necesario
            Padre updatedPadre = repository.save(padre);
            return Optional.of(convertToDto(updatedPadre));
        }
        return Optional.empty();
    }

    @Override
    public Padre asignarEstudiante(Long id_padre, Long id_estudiante) {
        Padre padre = repository.findById(id_padre)
                .orElseThrow(() -> new RuntimeException("Padre no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(id_estudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        padre.getEstudiantes().add(estudiante);
        estudiante.setPadre(padre);
        repository.save(padre);
        estudianteRepository.save(estudiante);
        return padre;
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    // Método para convertir una entidad Padre a un DTO
    private PadreDto convertToDto(Padre padre) {
        return new PadreDto(
                padre.getId_padre(),
                padre.getNombre(),
                padre.getEmail(),
                padre.getContraseña(), null);
    }

    public void asignarEstudiantes(Padre padre, Set<Long> estudiantesIds) {
        Set<Estudiante> estudiantes = estudiantesIds.stream()
                .map(id -> estudianteRepository.findById(id)
                        .orElseThrow())
                .collect(Collectors.toSet());
        padre.setEstudiantes(estudiantes);
    }
}
