package com.management.projects.dto;

import com.management.projects.role.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentDTO {

    private String boardId;
    private String projectId;
    private String activityId;
    private String taskId;
    private String email;

}
