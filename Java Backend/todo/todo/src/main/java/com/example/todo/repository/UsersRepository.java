package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Users findOne(Long id);

}
