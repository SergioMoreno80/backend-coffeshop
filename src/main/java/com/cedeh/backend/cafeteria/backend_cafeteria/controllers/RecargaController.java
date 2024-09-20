package com.cedeh.backend.cafeteria.backend_cafeteria.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.RecargasDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.services.RecargaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recargas")
@CrossOrigin(originPatterns = "*")
public class RecargaController {

    @Autowired
    private RecargaService service;

    @GetMapping
    public List<RecargasDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<RecargasDto> recargasOptional = service.findById(id);
        if (recargasOptional.isPresent()) {
            return ResponseEntity.ok(recargasOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @ModelAttribute RecargasDto recargas,
            BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(recargas));
        } catch (Exception e) {
            String errorMessage = "Error al crear recargas: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @ModelAttribute RecargasDto recargas,
            BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<RecargasDto> o = service.update(recargas, id);
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<RecargasDto> o = service.findById(id);
        if (o.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build();
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

    @GetMapping("/reporte")
    public ResponseEntity<?> getRecargasPorEstudiante(@RequestParam Long id_estudiante) {
        List<RecargasDto> recargasConSaldo = service.findRecargasWithSaldo(id_estudiante);
        if (recargasConSaldo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron recargas para el estudiante.");
        }
        return ResponseEntity.ok(recargasConSaldo);
    }

}
