package com.main.serv.security;

import com.main.serv.entity.User;
import com.main.serv.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(Long.parseLong(userId))
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with user Id: "+ userId));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getKey().getRoleName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUserId().toString(),
                user.getPassword(),
                authorities);
    }
}
