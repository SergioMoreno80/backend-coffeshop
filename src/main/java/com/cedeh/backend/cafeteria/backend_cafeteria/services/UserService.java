package com.cedeh.backend.cafeteria.backend_cafeteria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cedeh.backend.cafeteria.backend_cafeteria.models.entities.User;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.request.UserRequest;
import com.cedeh.backend.cafeteria.backend_cafeteria.models.dto.UserDto;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findAll(Pageable pageable);

    Optional<UserDto> findById(Long id);

    UserDto save(User user);
    Optional<UserDto> update(UserRequest user, Long id);

    void remove(Long id);
}
