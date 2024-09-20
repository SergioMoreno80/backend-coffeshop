package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto;

import java.math.BigDecimal;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Padre;

public class EstudianteDto {

    private Long id_estudiante;
    private String Nombre;
    private String Apellido;
    private BigDecimal Saldo;
    private String numeroIdentificacion;
    private String gradoEstudios;
    private Padre padre;

    public EstudianteDto(Long id_estudiante, String Nombre, String Apellido, BigDecimal saldo, 
    String numeroIdentificacion, String string, Padre padre) {
        super();
        this.id_estudiante = id_estudiante;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Saldo = saldo;
        this.numeroIdentificacion = numeroIdentificacion;
        this.padre = padre;
    }

   

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public BigDecimal getSaldo() {
        return Saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        Saldo = saldo;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getGradoEstudios() {
        return gradoEstudios;
    }

    public void setGradoEstudios(String gradoEstudios) {
        this.gradoEstudios = gradoEstudios;
    }



    public Long getId_estudiante() {
        return id_estudiante;
    }



    public void setId_estudiante(Long id_estudiante) {
        this.id_estudiante = id_estudiante;
    }



    public Padre getPadre() {
        return padre;
    }



    public void setPadre(Padre padre) {
        this.padre = padre;
    }

}
