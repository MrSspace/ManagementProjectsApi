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
public class ActivityResponse {

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private UserResponse activityManager;
    private List<UserResponse> collaborators;
    private List<TaskResponse> taskActivities;

}
