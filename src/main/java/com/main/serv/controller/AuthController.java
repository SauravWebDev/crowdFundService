package com.main.serv.controller;

import com.main.serv.dtos.JWTAuthResponse;
import com.main.serv.dtos.LoginDto;
import com.main.serv.dtos.SignupDto;
import com.main.serv.dtos.response.SignInResponse;
import com.main.serv.dtos.response.SignupResponse;
import com.main.serv.exception.ApiException;
import com.main.serv.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<SignInResponse> login(@Valid @RequestBody LoginDto loginDto){
        SignInResponse signInRes = new SignInResponse();

        try {
            SignInResponse res = authService.login(loginDto);
            return ResponseEntity.ok(res);
        }catch (ApiException e) {
            signInRes.setErrorMsg(e.getMessage());
            return new ResponseEntity<>(signInRes, e.getStatus());
        }
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<SignupResponse> register(@Valid @RequestBody SignupDto signupDto){
        SignupResponse res = new SignupResponse();
        try {
           authService.signUp(signupDto).getBody();
           res.setMsg("User is created successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (ApiException e) {
            res.setErrorMsg(e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }

    }
}
