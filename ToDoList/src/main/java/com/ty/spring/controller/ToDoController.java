package com.ty.spring.controller;

import com.ty.spring.entity.Todo;
import com.ty.spring.entity.User;
import com.ty.spring.repository.UserRepository;
import com.ty.spring.service.ToDoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ToDoController {

    private final ToDoService toDoService;
    private final UserRepository userRepository;

    public ToDoController(ToDoService toDoService, UserRepository userRepository) {
        this.toDoService = toDoService;
        this.userRepository = userRepository;
    }

    @GetMapping("/todos")
    public String listTodos(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        model.addAttribute("todos", toDoService.findByUser(user));
        model.addAttribute("newTodo", new Todo());
        return "todos";
    }

    @PostMapping("/todos")
    public String addTodo(@ModelAttribute Todo newTodo, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        newTodo.setUser(user);
        toDoService.save(newTodo);
        return "redirect:/todos";
    }

    @GetMapping("/todos/delete/{id}")
    public String delete(@PathVariable Long id) {
        toDoService.delete(id);
        return "redirect:/todos";
    }
}
