package com.management.projects.service;

import com.management.projects.domain.Board;
import com.management.projects.domain.Project;
import com.management.projects.domain.WorkAssignmentKey;
import com.management.projects.repository.UserRepository;
import com.management.projects.role.Permission;
import com.management.projects.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkAssignmentKeyService {

    @Autowired
    private UserRepository userRepository;
    private WorkAssignmentKey workKey = new WorkAssignmentKey();
    private List<WorkAssignmentKey> workAssignments = new ArrayList<>();

    public void boardAssign(Board board, User user, Permission permission){
        workKey.setBoardId(board.getId());

        workKey.setProjectId(0);
        workKey.setActivityId(0);
        workKey.setTaskId(0);

        workKey.setPermission(permission);

        workAssignments.add(workKey);
        user.setWorkAssignmentKeys(workAssignments);

        userRepository.save(user);
        workAssignments.clear();
    }

    public void projectAssign(Board board, Project project, User user, Permission permission){
        workKey.setBoardId(board.getId());
        workKey.setProjectId(project.getId());

        workKey.setActivityId(0);
        workKey.setTaskId(0);

        workKey.setPermission(permission);

        workAssignments.add(workKey);
        user.setWorkAssignmentKeys(workAssignments);

        userRepository.save(user);
        workAssignments.clear();
    }

}
