package com.management.projects.controller;

import com.management.projects.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users-view")
@RequiredArgsConstructor
public class UserViewController {

    private final UserService service;



}
