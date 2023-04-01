package com.management.projects.dto.response;

import com.management.projects.dto.NameEmail;
import com.management.projects.dto.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResponse {

    private String boardId;
    private String projectId;
    private String activityId;
    private String name;
    private String description;
    private NameEmail manager;
    private String startDate;
    private String endDate;
    private List<NameEmail> collaborators;
    private List<TaskDTO> tasks;

}
