package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class ProductoDto {

    private Long id_producto;
    private String Nombre;
    private String Descripcion;
    private BigDecimal Precio;
    private String Categoria; // Nuevo campo agregado
    private String imagenDireccion;
    private MultipartFile imagen; // Columna para almacenar la imagen en formato de bytes


    public ProductoDto(Long id_producto, String Nombre, String Descripcion, BigDecimal Precio, String Categoria,
            String imagenDireccion, MultipartFile imagen) {
        super();
        this.id_producto = id_producto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Categoria = Categoria;
        this.imagenDireccion = imagenDireccion;
        this.imagen = imagen;

    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

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

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }

}
