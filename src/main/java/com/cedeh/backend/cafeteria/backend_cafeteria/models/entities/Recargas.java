package com.cedeh.backend.cafeteria.backend_cafeteria.models.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Recargas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_recarga;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    @NotNull
    private Estudiante estudiante;

    @NotNull
    private BigDecimal monto;

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    private String Observaciones;

    public Long getId_recarga() {
        return id_recarga;
    }

    public void setId_recarga(Long id_recarga) {
        this.id_recarga = id_recarga;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }


}
