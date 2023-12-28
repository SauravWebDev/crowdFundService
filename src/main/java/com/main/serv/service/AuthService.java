package com.main.serv.service;

import com.main.serv.dtos.LoginDto;
import com.main.serv.dtos.SignupDto;
import com.main.serv.dtos.response.SignInResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    SignInResponse login(LoginDto loginDto);
    ResponseEntity<String> signUp(SignupDto registerDto);
}
