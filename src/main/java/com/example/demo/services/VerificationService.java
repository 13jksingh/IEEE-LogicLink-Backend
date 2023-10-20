package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.VerificationToken;
import com.example.demo.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional
    public VerificationToken getVerificationToken(User user) {
        return verificationTokenRepository.save(
                VerificationToken.builder().user(user).build()
        );
    }
}