package com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.ComprasDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.DetalleDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Compras;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Detalle;

public class DtoMapperCompras {
    private Compras compras;

    private DtoMapperCompras() {
    }

    public static DtoMapperCompras builder() {
        return new DtoMapperCompras();
    }

    public DtoMapperCompras setCompras(Compras compras) {
        this.compras = compras;
        return this;
    }

    public ComprasDto build() {
        if (compras == null) {
            throw new RuntimeException("Debe pasar el entity !");
        }
// Convertir la lista de Detalle a DetalleDto
        List<DetalleDto> detallesDto = compras.getDetallesCompra()
                .stream()
                .map(this::mapDetalleToDetalleDto)
                .collect(Collectors.toList());

        return new ComprasDto(this.compras.getId_compra().intValue(), compras.getEstudiante().getId_estudiante().intValue(),
        compras.getFecha(), detallesDto, compras.getEstudiante().getSaldo());

    }

     // Método para mapear Detalle a DetalleDto
    private DetalleDto mapDetalleToDetalleDto(Detalle detalle) {
        DetalleDto detalleDto = new DetalleDto();
        detalleDto.setId_producto(detalle.getProducto().getId_producto().intValue());
        detalleDto.setNombre_producto(detalle.getProducto().getNombre()); // Obtén el nombre del producto
        detalleDto.setCantidad(detalle.getCantidad());
        detalleDto.setPrecio_unitario(detalle.getPrecio_unitario());
        detalleDto.setTotal(detalle.getTotal());
        return detalleDto;
    }

}
