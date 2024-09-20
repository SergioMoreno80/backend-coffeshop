package com.cedeh.backend.cafeteria.backend_cafeteria.controllers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.ComprasDto;
// import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Detalle;
import com.cedeh.backend.cafeteria.backend_cafeteria.services.ComprasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/compras")
@CrossOrigin(originPatterns = "*")
public class ComprasController {
    @Autowired
    private ComprasService service;

    @GetMapping
    public List<ComprasDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<ComprasDto> comprasOptional = service.findById(id);
        if (comprasOptional.isPresent()) {
            return ResponseEntity.ok(comprasOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // @PostMapping
    // public ResponseEntity<?> create(
    // @Valid @ModelAttribute ComprasDto compras,
    // BindingResult result) {
    // if (result.hasErrors()) {
    // return validation(result);
    // }
    // try {
    // return ResponseEntity.status(HttpStatus.CREATED).body(service.save(compras));
    // } catch (Exception e) {
    // String errorMessage = "Error al crear compras: " + e.getMessage();
    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    // }
    // }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Valid ComprasDto compras,
            BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        try {
            // Lógica para guardar la compra
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(compras));
        } catch (Exception e) {
            String errorMessage = "Error al crear compras: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @ModelAttribute ComprasDto compras,
            BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<ComprasDto> o = service.update(compras, id);
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<ComprasDto> o = service.findById(id);
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
    public ResponseEntity<?> getReporteVentasPorEstudiante(
            @RequestParam Long id_estudiante,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        // Asegúrate de que el final del día esté incluido
        LocalDateTime adjustedFechaFin = fechaFin.with(LocalTime.MAX);
        List<ComprasDto> compras = service.findComprasByEstudianteAndFecha(id_estudiante, fechaInicio, adjustedFechaFin);

        if (compras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(compras);
    }
}
