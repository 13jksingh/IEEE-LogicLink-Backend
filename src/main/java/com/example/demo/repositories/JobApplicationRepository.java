package com.example.demo.repositories;

import com.example.demo.entities.JobApplication;
import com.example.demo.entities.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {

}
