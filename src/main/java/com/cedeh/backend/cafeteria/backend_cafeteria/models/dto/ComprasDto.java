package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
// import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Detalle;


public class ComprasDto {

    private Integer id_compra;
    private Integer id_estudiante; // ID del estudiante, no el objeto completo
    private LocalDateTime fecha;
    private List<DetalleDto> detallesCompra; // Usar DetalleDto en lugar de Detalle
    private BigDecimal nuevoSaldo; // Nuevo saldo a actualizar
    // private BigDecimal newVal; // Nuevo saldo a actualizar


    public ComprasDto() {
        // Constructor por defecto
    }

    public ComprasDto(Integer id_compra, Integer id_estudiante, LocalDateTime fecha, List<DetalleDto> detallesCompra,
            BigDecimal nuevoSaldo ) {
        super();
        this.id_compra = id_compra;
        this.id_estudiante = id_estudiante;
        this.fecha = fecha;
        this.detallesCompra = detallesCompra;
        this.nuevoSaldo = nuevoSaldo;

    }



    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public BigDecimal getNuevoSaldo() {
        return nuevoSaldo;
    }

    public void setNuevoSaldo(BigDecimal nuevoSaldo) {
        this.nuevoSaldo = nuevoSaldo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<DetalleDto> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleDto> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }



}
