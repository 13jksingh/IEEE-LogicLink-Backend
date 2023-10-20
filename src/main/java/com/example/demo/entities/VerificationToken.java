package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID token;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;

    @CreationTimestamp
    private LocalDateTime createdDate;
}