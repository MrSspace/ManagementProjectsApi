package com.management.projects.domain;

import com.management.projects.role.Permission;
import lombok.Data;

@Data
public class WorkAssignmentKey {
    private String boardId;
    private String projectId;
    private String taskId;
    private Permission role;

    public WorkAssignmentKey(String boardId, Permission role) {
        this.boardId = boardId;
        this.role = role;
    }

    public WorkAssignmentKey(String boardId, String projectId, Permission role) {
        this.boardId = boardId;
        this.projectId = projectId;
        this.role = role;
    }

    public WorkAssignmentKey(String boardId, String projectId, String taskId, Permission role) {
        this.boardId = boardId;
        this.projectId = projectId;
        this.taskId = taskId;
        this.role = role;
    }

}
