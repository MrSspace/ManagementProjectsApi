package com.management.projects.service;

import com.management.projects.domain.Task;
import com.management.projects.dto.TaskDTO;
import com.management.projects.repository.BoardRepository;
import com.management.projects.repository.UserRepository;
import com.management.projects.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.management.projects.util.DateFormatter.parseStringToDateInYyyyMmDdFormat;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public void createTask(TaskDTO request){
        User user = userRepository.findByEmail(request.getManager().getEmail());
        Task task = new Task();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setTaskManager(user);
        task.setStartDate(parseStringToDateInYyyyMmDdFormat(request.getStartDate()));
        task.setEndDate(parseStringToDateInYyyyMmDdFormat(request.getEndDate()));
        //List<Task> tasks = activity.getTasks();
        //tasks.add(task);
        //activity.setTasks(tasks);
        //boardRepository.save(board);
    }

    public void updateTask(){}
    public void deleteTask(){}

}
