package com.example.todo.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
