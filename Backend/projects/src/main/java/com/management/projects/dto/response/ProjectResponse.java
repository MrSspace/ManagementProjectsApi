package com.management.projects.dto.response;

import com.management.projects.dto.NameEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {

    private String boardId;
    private String projectId;
    private String name;
    private String description;
    private NameEmail manager;
    private List<NameEmail> collaborators;
    private List<ActivityResponse> activities;

}
