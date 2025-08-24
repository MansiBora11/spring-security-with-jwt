package com.example.spring_security_learning_jwt.controller;

import com.example.spring_security_learning_jwt.entity.User;
import com.example.spring_security_learning_jwt.repository.UserRepository;
import com.example.spring_security_learning_jwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User register successfully.";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return jwtUtil.generateToken(user.getUsername());
        }else{
            throw new RuntimeException("Invalid credentials.");
        }
    }
}
