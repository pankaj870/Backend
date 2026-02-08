
package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.model.TodoItems;

@Repository

public interface TodoRepository extends JpaRepository<TodoItems, Long> {

}
