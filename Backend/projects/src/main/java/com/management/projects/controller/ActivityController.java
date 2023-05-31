package com.management.projects.controller;

import com.management.projects.dto.response.ActivityResponse;
import com.management.projects.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    public ResponseEntity<ActivityResponse> loadActivity(
            @RequestParam(name = "boardId") String boardId,
            @RequestParam(name = "projectId") String projectId,
            @RequestParam(name = "activityId") String activityId){
        return new ResponseEntity<>(activityService.loadActivity(boardId,projectId,activityId), HttpStatus.OK);
    }

}
