package com.example.demo.services;

import com.example.demo.entities.JobPosting;
import com.example.demo.entities.User;
import com.example.demo.enums.JobStatus;
import com.example.demo.exception.UserRegistrationException;
import com.example.demo.repositories.JobPostingRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class JobPostingService {
    JobPostingRepository jobPostingRepository;
    UserRepository userRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository,UserRepository userRepository) {
        this.jobPostingRepository = jobPostingRepository;
        this.userRepository=userRepository;
    }

    public void createPost(JobPosting jobPosting, String email) {
        jobPosting.setPostedDate(LocalDateTime.now());
        jobPosting.setExpiryDate(LocalDateTime.now().plusDays(15));

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User employer = userOptional.get();
            jobPosting.setEmployer(employer);
            jobPosting.setStatus(JobStatus.OPEN);
            jobPostingRepository.save(jobPosting);
        } else {
            // Handle the case when user with the given email is not found
            throw new UserRegistrationException("User with email " + email + " not found.",HttpStatus.NOT_FOUND);
        }
    }

    public List<JobPosting> getAll(){
        return jobPostingRepository.findAll();
    }
}
