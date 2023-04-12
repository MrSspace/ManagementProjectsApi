package com.management.projects.controller;

import com.management.projects.dto.AssignmentDTO;
import com.management.projects.dto.response.ProjectResponse;
import com.management.projects.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<ProjectResponse> loadProject(
            @RequestParam(name = "boardId") String boardId,
            @RequestParam(name = "projectId") String projectId){
        return new ResponseEntity<>(projectService.loadProject(boardId,projectId), HttpStatus.OK);
    }

    @PostMapping("/collaborator")
    public ResponseEntity<String> addCollaborator(@RequestBody AssignmentDTO request){
        projectService.addCollaboratorToProject(request);
        return new ResponseEntity<>("New collaborator added to the project", HttpStatus.CREATED);
    }

    @PatchMapping("/collaborator")
    public ResponseEntity<String> removeCollaborator(@RequestBody AssignmentDTO request){
        projectService.removeCollaboratorToProject(request);
        return new ResponseEntity<>("Collaborator removed from project", HttpStatus.OK);
    }

}
