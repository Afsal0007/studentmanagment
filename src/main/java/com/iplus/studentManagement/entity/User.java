package com.iplus.studentManagement.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role; // e.g., "ROLE_USER" or "ROLE_ADMIN"
    
	public User(String username, String password, String role) {
		super();
		
		this.username = username;
		this.password = password;
		this.role = role;
	}
	// Getter & Setter
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }


    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
