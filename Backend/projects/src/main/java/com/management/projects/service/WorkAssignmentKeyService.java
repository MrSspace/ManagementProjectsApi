package com.management.projects.service;

import com.management.projects.domain.*;
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

    public void assignKey(Board board, Project project, Activity activity,
                          Task task, User user, Permission permission){

        workKey.setBoardId(board.getId());

        workKey.setProjectId( project == null ? 0 : project.getId() );
        workKey.setActivityId( activity == null ? 0 : activity.getId() );
        workKey.setTaskId( task == null ? 0 : task.getId() );

        workKey.setPermission(permission);

        workAssignments = user.getWorkAssignmentKeys();
        workAssignments.add(workKey);
        user.setWorkAssignmentKeys(workAssignments);

        userRepository.save(user);
        workAssignments.clear();
    }

    public boolean isTheAssignmentKey(WorkAssignmentKey ... keys){
        WorkAssignmentKey key1 = keys[0];
        WorkAssignmentKey key2 = keys[1];

        return key1.equals(key2);
    }

}
