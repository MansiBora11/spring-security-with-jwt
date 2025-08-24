package com.example.spring_security_learning_jwt.service;

import com.example.spring_security_learning_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private UserRepository userRepository;

    // your dependency here
}
