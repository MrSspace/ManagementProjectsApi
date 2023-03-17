package com.management.projects.domainTest;

import com.management.projects.domain.Board;
import com.management.projects.domain.PermissionAssign;
import com.management.projects.domain.Project;
import com.management.projects.domain.Task;
import com.management.projects.exceptions.ObjectNotAllowedException;
import com.management.projects.role.Permission;
import com.management.projects.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PermissionAssingnTest {
    private User user = new User();
    private Permission role = Permission.COLLABORATOR;
    private Board board = new Board();
    private Project project = new Project();
    private Task task = new Task();

    @Test
    public void create_PermissionAssing_with_Board(){
        PermissionAssign permission = new PermissionAssign(board,user,role);
        Assertions.assertEquals(Board.class, permission.getContext().getClass());
    }

    @Test
    public void create_PermissionAssing_with_Project(){
        PermissionAssign permission = new PermissionAssign(project,user,role);
        Assertions.assertEquals(Project.class, permission.getContext().getClass());
    }

    @Test
    public void create_PermissionAssing_with_Task(){
        PermissionAssign permission = new PermissionAssign(task,user,role);
        Assertions.assertEquals(Task.class, permission.getContext().getClass());
    }

    @Test
    public void create_PermissionAssing_with_User(){
        Assertions.assertThrows(ObjectNotAllowedException.class,
                ()->new PermissionAssign(user,user,role));
    }

    @Test
    public void create_PermissionAssing_with_generic_Object(){
        Assertions.assertThrows(ObjectNotAllowedException.class,
                ()->new PermissionAssign(new Object(),user,role));
    }

    @Test
    public void create_PermissionAssing_with_null_Object(){
        Assertions.assertThrows(NullPointerException.class,
                ()->new PermissionAssign(null,user,role));
    }

    @Test
    public void create_PermissionAssing_with_null_User(){
        Assertions.assertThrows(NullPointerException.class,
                ()->new PermissionAssign(task,null,role));
    }

    @Test
    public void create_PermissionAssing_with_null_Role(){
        Assertions.assertThrows(NullPointerException.class,
                ()->new PermissionAssign(task,user,null));
    }

}
