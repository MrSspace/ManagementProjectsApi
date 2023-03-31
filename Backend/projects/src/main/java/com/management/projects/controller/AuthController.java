package com.management.projects.controller;

import com.management.projects.dto.request.AuthRequest;
import com.management.projects.dto.response.SimpleMessageResponse;
import com.management.projects.dto.request.RegisterRequest;
import com.management.projects.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<SimpleMessageResponse> register(@RequestBody RegisterRequest user){
        return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<SimpleMessageResponse> authenticate(@RequestBody AuthRequest request){
        return ResponseEntity.ok(service.authentication(request));
    }

}
