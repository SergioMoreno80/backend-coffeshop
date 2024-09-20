package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.ProductoDto;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.mapper.DtoMapperProductos;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Producto;
import com.cedeh.backend.cafeteria.backend_cafeteria.repositories.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<ProductoDto> findAll() {
        List<Producto> producto = (List<Producto>) repository.findAll();
        return producto.stream().map(u -> DtoMapperProductos.builder().setProducto(u).build())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoDto> findById(Long id) {
        return repository.findById(id).map(u -> DtoMapperProductos.builder().setProducto(u).build());
    }

    @Override
    @Transactional
    public ProductoDto save(ProductoDto Producto) {
        Boolean local = true; // Se estable true para pruebas locales, se debe cambiar a false antes de
                              // generar actualizacion para colocar en AWS AMAZON
        String directorioImagenes;
        // Verifica si el directorio existe, si no, créalo
        Producto newDb = new Producto();
        newDb.setNombre(Producto.getNombre());
        newDb.setDescripcion(Producto.getNombre());
        newDb.setPrecio(Producto.getPrecio());
        newDb.setCategoria(Producto.getCategoria());

        if (local) {
            directorioImagenes = "/Users/sergiomoreno/Documents/imagenes/";
        } else {
            directorioImagenes = "/home/ec2-user/imagenes/";
        }
        Path pathDirectorio = Paths.get(directorioImagenes);
		if (!Files.exists(pathDirectorio)) {
			try {
				Files.createDirectories(pathDirectorio);
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

        try {
			// Guardar la imagen en el directorio de imágenes
			if (Producto.getImagen() != null) {
				Path rutaImagen = Paths.get(directorioImagenes + Producto.getImagen().getOriginalFilename());
				Files.write(rutaImagen, Producto.getImagen().getBytes());
				newDb.setImagenDireccion(Producto.getImagen().getOriginalFilename());

			}
	
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DtoMapperProductos.builder().setProducto(repository.save(newDb)).build();
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<ProductoDto> update(ProductoDto Producto, Long id) {
        Optional<Producto> o = repository.findById(id);
        Producto fabOptional = null;
        Boolean local = true; // Se estable true para pruebas locales, se debe cambiar a false antes de
                              // generar actualizacion para colocar en AWS AMAZON
        String directorioImagenes;
        
        if (local) {
            directorioImagenes = "/Users/sergiomoreno/Documents/imagenes/";
        } else {
            directorioImagenes = "/home/ec2-user/imagenes/";
        }
        if (o.isPresent()) {
            Producto newDb = o.orElseThrow();
            Path pathDirectorio = Paths.get(directorioImagenes);
            if (!Files.exists(pathDirectorio)) {
                try {
                    Files.createDirectories(pathDirectorio);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
    
            try {
                // Guardar la imagen en el directorio de imágenes
                if (Producto.getImagen() != null) {
                    Path rutaImagen = Paths.get(directorioImagenes + Producto.getImagen().getOriginalFilename());
                    Files.write(rutaImagen, Producto.getImagen().getBytes());
                    newDb.setImagenDireccion(Producto.getImagen().getOriginalFilename());
    
                }
        
            } catch (java.io.IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            newDb.setNombre(Producto.getNombre());
            newDb.setDescripcion(Producto.getNombre());
            newDb.setPrecio(Producto.getPrecio());
            newDb.setCategoria(Producto.getCategoria());
            fabOptional = repository.save(newDb);
        }
        return Optional.ofNullable(DtoMapperProductos.builder().setProducto(fabOptional).build());
    }

}
