package com.main.serv.service;

import com.main.serv.dtos.LoginDto;
import com.main.serv.dtos.SignupDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    String login(LoginDto loginDto);
    ResponseEntity<String> signUp(SignupDto registerDto);
}
