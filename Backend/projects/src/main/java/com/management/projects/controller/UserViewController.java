package com.management.projects.controller;

import com.management.projects.dto.request.AuthRequest;
import com.management.projects.dto.request.CreateBoardRequest;
import com.management.projects.dto.request.UpdateNameOfUserRequest;
import com.management.projects.dto.response.BoardResponse;
import com.management.projects.dto.response.UserResponse;
import com.management.projects.service.BoardService;
import com.management.projects.service.UserService;
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

    @PostMapping("/create-board")
    public ResponseEntity<BoardResponse> createBoard(@RequestBody CreateBoardRequest request){
        return new ResponseEntity<>(boardService.createBoard(request), HttpStatus.CREATED);
    }

    @PatchMapping("/update/name")
    public ResponseEntity<UserResponse> modifyNameOfUser(@RequestBody UpdateNameOfUserRequest request){
        return new ResponseEntity<>(userService.updateNameOfUser(request), HttpStatus.OK );
    }

    @PatchMapping("/update/password")
    public ResponseEntity<String> modifyPassword(@RequestBody AuthRequest request){
        userService.updatePassword(request);
        return new ResponseEntity<>("Password modified",HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody AuthRequest request){
        userService.deleteUser(request);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }


}
