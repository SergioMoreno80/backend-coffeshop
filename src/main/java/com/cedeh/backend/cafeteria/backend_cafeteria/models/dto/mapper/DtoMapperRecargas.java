package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.RecargasDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Recargas;

public class DtoMapperRecargas {
    private Recargas recargas;

    private DtoMapperRecargas() {
    }

    public static DtoMapperRecargas builder() {
        return new DtoMapperRecargas();
    }

    public DtoMapperRecargas setRecargas(Recargas recargas) {
        this.recargas = recargas;
        return this;
    }

    public RecargasDto build() {
        if (recargas == null) {
            throw new RuntimeException("Debe pasar el entity !");
        }

        return new RecargasDto(this.recargas.getId_recarga(), recargas.getEstudiante().getId_estudiante(),
                recargas.getMonto(), recargas.getFecha(), // ,
                recargas.getObservaciones(), recargas.getEstudiante().getSaldo());

    }


}
