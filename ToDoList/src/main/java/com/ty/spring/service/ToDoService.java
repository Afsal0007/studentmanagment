package com.ty.spring.service;

import com.ty.spring.entity.Todo;
import com.ty.spring.entity.User;
import com.ty.spring.repository.ToDoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository repo;

    public ToDoService(ToDoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> findByUser(User user) { return repo.findByUser(user); }
    public void save(Todo todo) { repo.save(todo); }
    public void delete(Long id) { repo.deleteById(id); }
}
