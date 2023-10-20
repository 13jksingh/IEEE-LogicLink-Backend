package com.example.demo.entities;

import com.example.demo.enums.JobStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "job_postings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    @JsonIgnore
    private User employer;

    private String title;
    private String description;
    private String location;
    private String skillsRequired;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_date")
    private LocalDateTime postedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

}


