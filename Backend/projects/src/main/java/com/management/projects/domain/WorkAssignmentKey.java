package com.management.projects.domain;

import com.management.projects.role.Permission;
import lombok.Data;

@Data
public class WorkAssignmentKey {
    private String boardId;
    private String projectId;
    private String taskId;
    private String activityId;
    private Permission permission;

    public WorkAssignmentKey(String boardId, Permission permission) {
        this.boardId = boardId;
        this.permission = permission;
    }

    public WorkAssignmentKey(String boardId, String projectId, Permission permission) {
        this.boardId = boardId;
        this.projectId = projectId;
        this.permission = permission;
    }

    public WorkAssignmentKey(String boardId, String projectId, String taskId, Permission permission) {
        this.boardId = boardId;
        this.projectId = projectId;
        this.taskId = taskId;
        this.permission = permission;
    }

    public WorkAssignmentKey(String boardId,String projectId,String taskId,String activityId,Permission permission) {
        this.boardId = boardId;
        this.projectId = projectId;
        this.taskId = taskId;
        this.activityId = activityId;
        this.permission = permission;
    }

}
