package com.main.serv.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.serv.dtos.LoginDto;
import com.main.serv.dtos.SignupDto;
import com.main.serv.entity.RoleUserMappingKey;
import com.main.serv.entity.Roles;
import com.main.serv.entity.User;
import com.main.serv.exception.ApiException;
import com.main.serv.repository.RoleRepository;
import com.main.serv.repository.UserRepository;
import com.main.serv.security.JwtTokenProvider;
import com.main.serv.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleRepository = roleRepository;
    }
    @Override
    public String login(LoginDto loginDto) {
        if(!userRepository.existsByEmail(loginDto.getEmailId())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email Id does not exists!.");
        }
        Optional<User> user = userRepository.findByEmail(loginDto.getEmailId());

        System.out.println("paasss "+ user.get().getPassword());
        if(passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {
            Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.get().getUserId(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return jwtTokenProvider.generateToken(authentication);
        } else {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Password does not match.");
        }


    }

    @Override
    public ResponseEntity<String> signUp(SignupDto signupDto)  {
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println("sign up details  are "+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(signupDto));
        if(userRepository.existsByEmail(signupDto.getEmailId())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }
        if(userRepository.existsByMobile(signupDto.getMobile())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Phone Number is already exists!.");
        }
        User user = new User();
        user.setEmail(signupDto.getEmailId());
        user.setMobile(signupDto.getMobile());
        user.setCountryCode(signupDto.getCountryCode());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        userRepository.save(user);
        Roles role = new Roles();
        RoleUserMappingKey map = new RoleUserMappingKey();

        map.setRoleName("VIEW");
        map.setUserId(userRepository.findByEmail(user.getEmail()).get().getUserId());
        role.setKey(map);
        roleRepository.save(role);
        return new ResponseEntity<>("User registered successfully!.",HttpStatus.CREATED);
    }
}
