package com.management.projects.controller;

import com.management.projects.dto.request.CreateBoardRequest;
import com.management.projects.dto.response.BoardResponse;
import com.management.projects.service.BoardService;
import com.management.projects.service.UserService;
import com.management.projects.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users-view")
@RequiredArgsConstructor
public class UserViewController {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello, Api V1 from users-view uwu");
    }

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@RequestBody CreateBoardRequest request){
        return new ResponseEntity<>(boardService.createBoard(request), HttpStatus.CREATED);
    }


}
