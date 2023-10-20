package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
