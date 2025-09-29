package com.ty.spring.controller;

import com.ty.spring.entity.User;
import com.ty.spring.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User()); // ✅ added
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        userService.save(user, passwordEncoder);
        return "redirect:/login";
    }
}
