package com.example.demo.repositories;

import com.example.demo.entities.JobPosting;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPosting,Long> {

}
