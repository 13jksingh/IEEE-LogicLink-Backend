package com.example.demo.controller;

import com.example.demo.constants.Constants;
import com.example.demo.entities.JobPosting;
import com.example.demo.entities.User;
import com.example.demo.services.JobPostingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobPostingController {
    JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @PostMapping
    @PreAuthorize(Constants.HAS_ROLE_EMPLOYER)
    public ResponseEntity<JobPosting> createPost(@RequestBody @Validated JobPosting jobPosting){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        String email = (String) authentication.getPrincipal();
        jobPostingService.createPost(jobPosting,email);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobPosting);
    }

    @GetMapping
    @PreAuthorize(Constants.HAS_ANY_ROLE)
    public ResponseEntity<List<JobPosting>> getAllPost(){
        return ResponseEntity.ok(jobPostingService.getAll());
    }
}