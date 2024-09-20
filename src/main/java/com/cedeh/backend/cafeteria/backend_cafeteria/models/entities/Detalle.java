package com.cedeh.backend.cafeteria.backend_cafeteria.models.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "detalles_compra")
public class Detalle {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    @NotNull
    private Compras compra;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    @NotNull
    private Producto producto;

    @NotNull
    private Integer cantidad;

    @NotNull
    private BigDecimal precio_unitario;

    @NotNull
    private BigDecimal total;

    public Long getId_detalle() {
        return id_detalle;
    }
    public void setId_detalle(Long id_detalle) {
        this.id_detalle = id_detalle;
    }
    
    public Compras getCompra() {
        return compra;
    }
    public void setCompra(Compras compra) {
        this.compra = compra;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
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
    
}
