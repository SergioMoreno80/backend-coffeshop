package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.EstudianteDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;

public class DtoMapperEstudiante {
    private Estudiante estudiante;

	private DtoMapperEstudiante() {
	}

	public static DtoMapperEstudiante builder() {
		return new DtoMapperEstudiante();
	}

	public DtoMapperEstudiante setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
		return this;
	}

	public EstudianteDto build() {
		if (estudiante == null) {
			throw new RuntimeException("Debe pasar el entity !");
		}

		return new EstudianteDto(this.estudiante.getId_estudiante(), estudiante.getNombre(),
				estudiante.getApellido(), estudiante.getSaldo(), // ,
				estudiante.getNumeroIdentificacion(), estudiante.getGradoEstudios(), estudiante.getPadre());

	}

}
