package com.cedeh.backend.cafeteria.backend_cafeteria.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.Role;

public interface RoleRepository
        extends CrudRepository<Role, Long> {
        Optional<Role> findByName(String name);
}
