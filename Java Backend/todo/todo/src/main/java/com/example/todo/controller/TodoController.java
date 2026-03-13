package com.example.todo.controller;

import java.util.List;
import com.example.todo.DTO.UpdateDto;

import com.example.todo.model.TodoItems;
import com.example.todo.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<TodoItems> getAllTodos(@RequestParam(required = false) String status) {
        // if (status != null) {
        // return todoRepository.findByStatus(Boolean.parseBoolean(status));
        // }
        return todoRepository.findAll();
    }

    @PatchMapping("/{id}")
    public TodoItems updateTodo(@PathVariable Long id, @RequestBody UpdateDto todoUpdate) {
        TodoItems existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (todoUpdate.getStatus() != null) {
            existingTodo.setStatus(todoUpdate.getStatus());
        }

        return todoRepository.save(existingTodo);
    }

    @PostMapping
    public TodoItems createTodo(@RequestBody @Valid TodoItems todoItem) {
        return todoRepository.save(todoItem);
    }
}
