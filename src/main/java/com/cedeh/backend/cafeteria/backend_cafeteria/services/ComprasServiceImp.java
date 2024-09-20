package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.ComprasDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.DetalleDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper.DtoMapperCompras;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Compras;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Detalle;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Estudiante;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Producto;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.ComprasRepository;
// import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.DetalleRepository;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.EstudianteRepository;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.ProductoRepository;

@Service
public class ComprasServiceImp implements ComprasService {

    @Autowired
    private ComprasRepository repository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ComprasDto> findAll() {
        List<Compras> compras = (List<Compras>) repository.findAll();
        return compras.stream().map(u -> DtoMapperCompras.builder().setCompras(u).build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ComprasDto> findById(Long id) {
        return repository.findById(id).map(u -> DtoMapperCompras.builder().setCompras(u).build());
    }

    @Override
    public ComprasDto save(ComprasDto comprasDto) {

        Compras compras = new Compras();
        if (comprasDto.getId_estudiante() == null) {
            throw new RuntimeException("Estudiante ID is null");
        }
        Optional<Estudiante> optionalEst = estudianteRepository.findById(Long.valueOf(comprasDto.getId_estudiante()));
        // Verificar si el estudiante existe
        if (!optionalEst.isPresent()) {
            throw new RuntimeException("Estudiante no encontrado");
        }

        Estudiante estudiante = optionalEst.get();
        estudiante.setSaldo(comprasDto.getNuevoSaldo());
        compras.setEstudiante(estudiante);
        compras.setFecha(comprasDto.getFecha());

        // Actualizar el saldo del estudiante
        // estudiante.setSaldo(estudiante.getSaldo().add( recargas.getMonto()));
        estudiante.setSaldo(comprasDto.getNuevoSaldo());
        estudianteRepository.save(estudiante);
        for (DetalleDto detalleDto : comprasDto.getDetallesCompra()) {
            Detalle detalle = new Detalle();
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setPrecio_unitario(detalleDto.getPrecio_unitario());
            detalle.setTotal(detalleDto.getTotal());

            // Buscar el producto en la base de datos y asignarlo
            Producto producto = productoRepository.findById(detalleDto.getId_producto().longValue())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            detalle.setProducto(producto);
            compras.addDetalle(detalle); // Agrega el detalle a la compra

        }
        // Calcula el total de la compra
        BigDecimal totalCompra = compras.getDetallesCompra().stream()
                .map(Detalle::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        compras.setTotal(totalCompra);

        return DtoMapperCompras.builder().setCompras(repository.save(compras)).build();

    }

    @Override
    public Optional<ComprasDto> update(ComprasDto compras, Long id) {
        Optional<Compras> o = repository.findById(id);
        Compras fabOptional = null;
        Optional<Estudiante> optionalEst = estudianteRepository.findById(Long.valueOf(compras.getId_estudiante()));
        // Verificar si el estudiante existe
        if (!optionalEst.isPresent()) {
            throw new RuntimeException("Estudiante no encontrado");
        }

        Estudiante estudiante = optionalEst.get();
        if (o.isPresent()) {
            Compras newDb = o.orElseThrow();
            newDb.setEstudiante(estudiante);
            newDb.setFecha(compras.getFecha());
            fabOptional = repository.save(newDb);
        }
        return Optional.ofNullable(DtoMapperCompras.builder().setCompras(fabOptional).build());
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ComprasDto> findComprasByEstudianteAndFecha(Long id_estudiante, LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {
        // Query personalizada para obtener las compras del estudiante en el rango de
        // fechas
        // List<Compras> compras =
        // repository.findByEstudianteAndFechaBetween(id_estudiante, fechaInicio,
        // fechaFin);
        Optional<Estudiante> optionalEst = estudianteRepository.findById(Long.valueOf(id_estudiante));
        Estudiante estudiante = optionalEst.get();
        List<Compras> compras = repository.findByEstudianteAndFechaBetween(estudiante, fechaInicio, fechaFin);

        return compras.stream()
                .map(compra -> DtoMapperCompras.builder().setCompras(compra).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ComprasDto> findComprasByFecha(LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {
        // Query personalizada para obtener las compras del estudiante en el rango de
        // fechas
        // List<Compras> compras =
        // repository.findByEstudianteAndFechaBetween(id_estudiante, fechaInicio,
        // fechaFin);
       
        List<Compras> compras = repository.findByFechaBetween(fechaInicio, fechaFin);

        return compras.stream()
                .map(compra -> DtoMapperCompras.builder().setCompras(compra).build())
                .collect(Collectors.toList());
    }

}
