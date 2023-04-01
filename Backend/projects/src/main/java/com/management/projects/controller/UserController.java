package com.management.projects.controller;

import com.management.projects.dto.request.AuthRequest;
import com.management.projects.dto.NameEmail;
import com.management.projects.dto.response.BoardResponse;
import com.management.projects.service.BoardService;
import com.management.projects.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello, Api V1 from users-view uwu");
    }

    @GetMapping("/board")
    public ResponseEntity< List<BoardResponse> > loadBoards(@RequestParam(name = "email") String userEmail){
        return new ResponseEntity<>(
                boardService.loadAllBoardsByUser( userService.getUserByEmail(userEmail) ),
                HttpStatus.CREATED);
    }

    @PostMapping("/board")
    public ResponseEntity<BoardResponse> createBoard(@RequestBody NameEmail request){
        return new ResponseEntity<>(boardService.createBoard(request), HttpStatus.CREATED);
    }

    @PatchMapping("/update/name")
    public ResponseEntity<NameEmail> modifyNameOfUser(@RequestBody NameEmail request){
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
