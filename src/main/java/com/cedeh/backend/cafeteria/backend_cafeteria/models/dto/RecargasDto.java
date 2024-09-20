package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RecargasDto {

    private Long id_recarga;

    private Long id_estudiante;

    private BigDecimal monto;

    private LocalDateTime fecha;

    private String Observaciones;

    private BigDecimal saldoActual; // Nuevo campo

    public RecargasDto(Long id_recarga, Long id_estudiante,
            BigDecimal monto, LocalDateTime fecha, String Observaciones, BigDecimal saldoActual) {
        super();
        this.id_recarga = id_recarga;
        this.id_estudiante = id_estudiante;
        this.fecha = fecha;
        this.monto = monto;
        this.Observaciones = Observaciones;
        this.saldoActual = saldoActual; // Nuevo campo

    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Long getId_recarga() {
        return id_recarga;
    }

    public void setId_recarga(Long id_recarga) {
        this.id_recarga = id_recarga;
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

    public Long getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Long id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

}
