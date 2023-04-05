package com.management.projects.domain;

import com.management.projects.role.Permission;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkAssignmentKey {

    @NotNull
    private ObjectId boardId;
    private int projectId;
    private int activityId;
    private int taskId;
    @NotNull
    private Permission permission;

    public WorkAssignmentKey(ObjectId boardId, Permission permission) {
        this.boardId = boardId;
        this.permission = permission;
    }

}
