package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto;

import java.util.HashSet;
import java.util.Set;

public class PadreDto {
    private Long id_padre;
    private String nombre;
    private String email;
    private String contraseña;
    // private Set<Estudiante> estudiantes = new HashSet<>();
    private Set<Long> estudiantesIds = new HashSet<>();


    public PadreDto(Long id_padre, String nombre, String email, String contraseña, Set<Long> estudiantesIds
    ) {
        this.id_padre = id_padre;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.estudiantesIds = estudiantesIds
        ;
    }



    public Long getId_padre() {
        return id_padre;
    }

    public void setId_padre(Long id_padre) {
        this.id_padre = id_padre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Set<Long> getEstudiantesIds() {
        return estudiantesIds;
    }

    public void setEstudiantesIds(Set<Long> estudiantesIds) {
        this.estudiantesIds = estudiantesIds;
    }

    // public Set<Estudiante> getEstudiantes() {
    //     return estudiantes;
    // }

    // public void setEstudiantes(Set<Estudiante> estudiantes) {
    //     this.estudiantes = estudiantes;
    // }

}
