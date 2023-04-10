package com.management.projects.controller;

import com.management.projects.dto.response.ProjectResponse;
import com.management.projects.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
