package com.main.serv.repository;

import com.main.serv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByMobile(String mobile);
    Boolean existsByUserId(Long userId);
    Boolean existsByEmail(String email);
    Boolean existsByMobile(String mobile);

}
