package com.example.demo.entities;

import com.example.demo.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobPosting jobPosting;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "application_date")
    private Date applicationDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
}


