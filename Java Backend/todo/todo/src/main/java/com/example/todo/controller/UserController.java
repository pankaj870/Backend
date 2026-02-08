package com.example.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.todo.DTO.UpdateDto;

import com.example.todo.model.Users;
import com.example.todo.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class UserController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(required = false) Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Users> user;
            if (id != null) {
                user = usersRepository.findOne(id);
            } else {
                user = usersRepository.findAll();
            }

            response.put("message", "success");
            response.put("result", user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("result", e.getMessage());
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users entity) {

        try {
            // check username exists
            if (usersRepository.existsByUsername(entity.getUsername())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Map.of(
                                "message", "error",
                                "result", "Username already exists"));
            }

            // check email exists
            if (usersRepository.existsByEmail(entity.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Map.of(
                                "message", "error",
                                "result", "Email already exists"));
            }

            Users savedUser = usersRepository.save(entity);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "success",
                            "result", savedUser));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "message", "error",
                            "result", e.getMessage()));
        }
    }

}
