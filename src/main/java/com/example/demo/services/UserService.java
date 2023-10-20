package com.example.demo.services;

import com.example.demo.entities.JobApplication;
import com.example.demo.entities.JobPosting;
import com.example.demo.entities.User;
import com.example.demo.exception.UserRegistrationException;
import com.example.demo.repositories.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user, PasswordEncoder passwordEncoder) {
//        if (!userRepository.existsByEmail(user.getEmail())) {
//            throw new UserRegistrationException("User with given email already exists",HttpStatus.BAD_REQUEST);
//        }
//        if (Strings.isBlank(user.getEmail()) || Strings.isBlank(user.getPassword())) {
//            throw new UserRegistrationException("One or more required fields are empty", HttpStatus.BAD_REQUEST);
//        }
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setJobPostings(new ArrayList<JobPosting>());
        user.setJobApplications(new ArrayList<JobApplication>());
        System.out.println(user);
        userRepository.save(user);
    }
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserRegistrationException("User with provided email does not exist", HttpStatus.NOT_FOUND)
        );
    }

}
