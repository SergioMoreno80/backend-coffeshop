package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto;

import java.math.BigDecimal;

public class DetalleDto {

    // private Long id_detalle;
    private Integer id_producto; // ID del producto, no el objeto completo
    private String nombre_producto; // Nombre del producto
    private Integer cantidad;
    private BigDecimal precio_unitario;
    private BigDecimal total;

    // Constructor vacío
    public DetalleDto() {
    }

    // Constructor completo
    public DetalleDto(Integer id_producto, String nombre_producto, Integer cantidad, BigDecimal precio_unitario,
            BigDecimal total) {
        // this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.total = total;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

}
