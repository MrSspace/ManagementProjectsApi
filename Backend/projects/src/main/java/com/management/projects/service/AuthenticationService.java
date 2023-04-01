package com.management.projects.service;

import com.management.projects.dto.request.AuthRequest;
import com.management.projects.dto.response.SimpleMessage;
import com.management.projects.dto.request.RegisterRequest;
import com.management.projects.exception.InvalidEmailException;
import com.management.projects.role.Role;
import com.management.projects.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.management.projects.util.RegexValidator.isEmail;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public SimpleMessage register(RegisterRequest request){
        emailValidation(request.getEmail());
        userService.createUser(request, Role.USER);
        return assignToken( loadUser(request.getEmail()) );
    }

    public SimpleMessage authentication(AuthRequest request){
        emailValidation(request.getEmail());
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword())
        );
        return assignToken( loadUser(request.getEmail()) );
    }

    private void emailValidation(String email){
        if (!isEmail(email)){
            throw new InvalidEmailException(email);
        }
    }

    private SimpleMessage assignToken(User user){
        String jwtToken = jwtService.generateJwtToken(user);
        return SimpleMessage.builder().message(jwtToken).build();
    }

    private User loadUser(String email){
        return userService.getUserByEmail(email);
    }

}
