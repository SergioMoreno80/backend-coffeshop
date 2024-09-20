package com.cedeh.backend.cafeteria.backend_cafeteria.models.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "compras")
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_compra;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    @NotNull
    private Estudiante estudiante;

    @NotNull
    private LocalDateTime fecha;

    // @OneToMany(mappedBy = "compra")
    // private List<Detalle> detallesCompra;
    @OneToMany(mappedBy = "compra", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Detalle> detallesCompra = new ArrayList<>(); // Inicializa la lista para evitar null pointers

    @NotNull
    private BigDecimal total = BigDecimal.ZERO; // Inicializa el total


    public Long getId_compra() {
        return id_compra;
    }

    public void setId_compra(Long id_compra) {
        this.id_compra = id_compra;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Detalle> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<Detalle> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    
 // Método para añadir un detalle a la compra
 public void addDetalle(Detalle detalle) {
    detallesCompra.add(detalle);
    detalle.setCompra(this); // Asegura la relación bidireccional
    calculateTotal(); // Calcula el total cada vez que se agrega un detalle
}

// Método para calcular el total de la compra
private void calculateTotal() {
    this.total = detallesCompra.stream()
        .map(Detalle::getTotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
}

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
