package com.management.projects.service;

import com.management.projects.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final BoardRepository boardRepository;

    public void createTask(){}
    public void updateTask(){}
    public void deleteTask(){}

}
