package com.management.projects.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private UserResponse projectManager;
    private List<UserResponse> collaborators;
    private List<ActivityResponse> activities;

}
