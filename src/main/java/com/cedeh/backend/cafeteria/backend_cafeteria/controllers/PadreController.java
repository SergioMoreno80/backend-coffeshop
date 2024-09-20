package com.cedeh.backend.cafeteria.backend_cafeteria.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.PadreDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Padre;
import com.cedeh.backend.cafeteria.backend_cafeteria.services.EstudianteService;
import com.cedeh.backend.cafeteria.backend_cafeteria.services.PadreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/padres")
@CrossOrigin(originPatterns = "*")
public class PadreController {

    @Autowired
    private PadreService service;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<PadreDto>> list() {
        List<PadreDto> padres = service.findAll();
        return ResponseEntity.ok(padres);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<PadreDto>> list(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<PadreDto> padres = service.findAll(pageable);
        return ResponseEntity.ok(padres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PadreDto> show(@PathVariable Long id) {
        Optional<PadreDto> padreDto = service.findById(id);

        return padreDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PadreDto padreDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Padre padre = convertToEntity(padreDto);
        try {
            // Lógica para guardar la compra
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(padre));
        } catch (Exception e) {
            String errorMessage = "Error al crear compras: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
        // PadreDto createdPadre = service.save(padre);
        // return ResponseEntity.status(HttpStatus.CREATED).body(createdPadre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody PadreDto padreDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<PadreDto> updatedPadre = service.update(padreDto, id);

        return updatedPadre.map(padre -> ResponseEntity.status(HttpStatus.CREATED).body(padre))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<PadreDto> padreDto = service.findById(id);

        if (padreDto.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }


    // Método de conversión entre DTO y entidad
    private Padre convertToEntity(PadreDto padreDto) {
        Padre padre = new Padre();
        padre.setId_padre(padreDto.getId_padre());
        padre.setNombre(padreDto.getNombre());
        padre.setEmail(padreDto.getEmail());

        // Convertir los IDs de estudiantes a entidades Estudiante
        Set<Estudiante> estudiantes = estudianteService.obtenerEstudiantesPorIds(padreDto.getEstudiantesIds());
        padre.setEstudiantes(estudiantes);

        // Asignar la contraseña si está presente
        if (padreDto.getContraseña() != null && !padreDto.getContraseña().isEmpty()) {
            padre.setContraseña(padreDto.getContraseña());
        }

        return padre;
    }

}
