package com.ty.spring.service;

import com.ty.spring.entity.User;
import com.ty.spring.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // Save a new user (PasswordEncoder passed from controller)
    public void save(User user, org.springframework.security.crypto.password.PasswordEncoder encoder) {
        // Ensure role is set
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        // Encode password
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    // Load user by username for Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Use default role if somehow null
        String role = user.getRole() != null ? user.getRole() : "USER";

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(role)
                .build();
    }
}
