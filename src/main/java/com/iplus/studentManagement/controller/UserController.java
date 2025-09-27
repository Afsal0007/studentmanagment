package com.iplus.studentManagement.controller;
import com.iplus.studentManagement.entity.User;
import com.iplus.studentManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    
    

    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	// Show signup page
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // Thymeleaf template signup.html
    }

    // Handle signup form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {

        if (userRepository.existsByUsername(username)) {
            model.addAttribute("error", "Username already taken!");
            return "signup";
        }

        // Encode password before saving to DB
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, role);

        userRepository.save(user);

        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Thymeleaf template login.html
    }
}
