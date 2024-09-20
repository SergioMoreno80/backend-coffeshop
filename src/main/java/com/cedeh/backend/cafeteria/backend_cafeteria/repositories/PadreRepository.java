package com.cedeh.backend.cafeteria.backend_cafeteria.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Padre;

@Repository
public interface PadreRepository extends CrudRepository<Padre, Long> {
    Optional<Padre> findByNombre(String nombre);

    // @Query("select u from Padre u where u.username=?1")
    // Optional<Padre> getUserByUsername(String username);

    Page<Padre> findAll(Pageable pageable);
}
