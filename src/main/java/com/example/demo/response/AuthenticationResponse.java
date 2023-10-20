package com.example.demo.response;

import com.example.demo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwtToken;
    private User user;
}