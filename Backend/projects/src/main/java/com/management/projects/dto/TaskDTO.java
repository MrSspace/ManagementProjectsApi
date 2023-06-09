package com.management.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String boardId;
    private String projectId;
    private String activityId;
    private String taskId;
    private String name;
    private String description;
    private NameEmail manager;
    private String startDate;
    private String endDate;

}
