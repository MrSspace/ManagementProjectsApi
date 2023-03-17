package com.management.projects.domain;

import com.management.projects.role.Permission;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class WorkAssignmentKey {
    @NotNull
    private ObjectId boardId;
    private ObjectId projectId;
    private ObjectId taskId;
    private ObjectId activityId;
    @NotNull
    private Permission permission;

    public WorkAssignmentKey(ObjectId boardId, Permission permission) {
        this.boardId = boardId;
        this.permission = permission;
    }

    public WorkAssignmentKey(ObjectId boardId, ObjectId projectId, Permission permission) {
        this.boardId = boardId;
        this.projectId = projectId;
        this.permission = permission;
    }

    public WorkAssignmentKey(ObjectId boardId, ObjectId projectId, ObjectId taskId, Permission permission) {
        this.boardId = boardId;
        this.projectId = projectId;
        this.taskId = taskId;
        this.permission = permission;
    }

    public WorkAssignmentKey(ObjectId boardId,ObjectId projectId,ObjectId taskId,ObjectId activityId,Permission permission) {
        this.boardId = boardId;
        this.projectId = projectId;
        this.taskId = taskId;
        this.activityId = activityId;
        this.permission = permission;
    }

}
