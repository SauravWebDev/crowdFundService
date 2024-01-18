package com.main.serv.service.impl;

import com.main.serv.dtos.LoginDto;
import com.main.serv.dtos.SignupDto;
import com.main.serv.dtos.response.SignInResponse;
import com.main.serv.entity.RoleUserMappingKey;
import com.main.serv.entity.Roles;
import com.main.serv.entity.User;
import com.main.serv.exception.ApiException;
import com.main.serv.repository.RoleRepository;
import com.main.serv.repository.UserRepository;
import com.main.serv.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    public AuthServiceImpl( UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public SignInResponse login(LoginDto loginDto) {
        if(!userRepository.existsByEmail(loginDto.getEmailId())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email Id does not exists!.");
        }
        Optional<User> user = userRepository.findByEmail(loginDto.getEmailId());
        System.out.println("paasss "+ user.get().getPassword());
        SignInResponse res = new SignInResponse();
        res.setErrorMsg(null);
        res.setProfile(true);
        res.setAccessToken("adsas");
        return res;
    }

    @Override
    public ResponseEntity<String> signUp(SignupDto signupDto)  {
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
        user.setPassword(signupDto.getPassword());
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
