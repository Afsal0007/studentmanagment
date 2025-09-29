package com.ty.spring.repository;

import com.ty.spring.entity.Todo;
import com.ty.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ToDoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}
