package com.management.projects.controller;

import com.management.projects.dto.AssignmentDTO;
import com.management.projects.dto.NameId;
import com.management.projects.dto.request.ProjectRequest;
import com.management.projects.dto.response.BoardResponse;
import com.management.projects.dto.response.ProjectResponse;
import com.management.projects.service.BoardService;
import com.management.projects.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<BoardResponse> loadBoard(@RequestParam(name = "boardId") String boardId){
        return new ResponseEntity<>(boardService.loadBoard(new ObjectId(boardId)), HttpStatus.OK);
    }

    @PostMapping("/project")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request){
        return new ResponseEntity<>(projectService.createProject(request), HttpStatus.CREATED);
    }

    @PostMapping("/collaborator")
    public ResponseEntity<String> addCollaborator(@RequestBody AssignmentDTO request){
        boardService.addCollaboratorToBoard(request);
        return new ResponseEntity<>("New collaborator added to the board", HttpStatus.CREATED);
    }

    @PatchMapping("/collaborator")
    public ResponseEntity<String> removeCollaborator(@RequestBody AssignmentDTO request){
        boardService.removeCollaboratorFromBoard(request);
        return new ResponseEntity<>("Collaborator removed from board", HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<String> renameBoard(@RequestBody NameId request){
        boardService.changeBoardName(request);
        return new ResponseEntity<>("Board name successfully changed", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBoard(@RequestBody NameId request){
        boardService.deleteBoard(request);
        return new ResponseEntity<>("Board name successfully changed", HttpStatus.OK);
    }

}
