package com.management.projects.domain;

import com.management.projects.exceptions.ObjectNotAllowedException;
import com.management.projects.role.Permission;
import com.management.projects.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PermissionAssign {
    private Object context;
    private User collaborator;
    private Permission role;

    public PermissionAssign(Object context,User collaborator, Permission role){
        verifyContext(context);
        verifyCollaborator(collaborator);
        verifyRole(role);
        this.context = context;
        this.collaborator = collaborator;
        this.role = role;
    }

    private void verifyContext(Object context){
        if (context == null){
            throw new NullPointerException("Context cannot be null.");
        }
        if (context.getClass() != Board.class
                && context.getClass() != Project.class
                && context.getClass() != Task.class){
            throw new ObjectNotAllowedException("Context must be: Board, Project or Task.");
        }
    }

    private void verifyCollaborator(User collaborator){
        if (collaborator == null){
            throw new NullPointerException("Collaborator cannot be null.");
        }
    }

    private void verifyRole(Permission role){
        if (role == null){
            throw new NullPointerException("Role cannot be null. Permission must be choose first");
        }
    }

}
