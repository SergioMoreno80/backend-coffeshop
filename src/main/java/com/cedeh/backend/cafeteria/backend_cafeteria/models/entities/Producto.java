package com.cedeh.backend.cafeteria.backend_cafeteria.models.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @NotNull
    private String Nombre;

    @NotNull
    private String Descripcion;

    @NotNull
    private BigDecimal Precio;

    @NotNull
    private String Categoria;  // Nuevo campo agregado

    private String imagenDireccion;

    @OneToMany(mappedBy = "producto")
    private List<Detalle> detallesCompra;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return Precio;
    }

    public void setPrecio(BigDecimal precio) {
        Precio = precio;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getImagenDireccion() {
        return imagenDireccion;
    }

    public void setImagenDireccion(String imagenDireccion) {
        this.imagenDireccion = imagenDireccion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public List<Detalle> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<Detalle> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    
    
}
