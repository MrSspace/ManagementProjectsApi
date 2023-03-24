package com.management.projects.service;

import com.management.projects.dto.AuthRequest;
import com.management.projects.dto.AuthResponse;
import com.management.projects.dto.RegisterRequest;
import com.management.projects.exception.InvalidEmailException;
import com.management.projects.repository.UserRepository;
import com.management.projects.role.Role;
import com.management.projects.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.management.projects.util.RegexValidator.isEmail;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request){
        emailValidation(request.getEmail());
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword())
        );
        user.setRoles(Collections.singletonList(Role.USER));
        repository.insert(user);
        return assignToken(user);
    }

    public AuthResponse authentication(AuthRequest request){
        emailValidation(request.getEmail());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = repository.findByEmail(request.getEmail());
        return assignToken(user);
    }

    private void emailValidation(String email){
        if (!isEmail(email)){
            throw new InvalidEmailException(email);
        }
    }

    private AuthResponse assignToken(User user){

        return new AuthResponse("hi");
    }

}
