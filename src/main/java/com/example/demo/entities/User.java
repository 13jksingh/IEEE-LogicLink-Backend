package com.example.demo.entities;

import com.example.demo.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_ieee", schema = "public")
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    private String email;

    private String password;

    private String contactNumber;

    private String address;

    private String skills;

    private String certifications;

    private String preferredLanguage;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @JsonIgnore
    private String bankAccountNumber;

    @OneToMany(mappedBy = "employer")
    @JsonIgnore
    private List<JobPosting> jobPostings = new ArrayList<JobPosting>();

    @OneToMany(mappedBy = "applicant")
    @JsonIgnore
    private List<JobApplication> jobApplications = new ArrayList<JobApplication>();

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toString()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
