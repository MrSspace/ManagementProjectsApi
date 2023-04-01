package com.management.projects.service;

import com.management.projects.domain.Board;
import com.management.projects.domain.Project;
import com.management.projects.dto.NameEmail;
import com.management.projects.dto.request.ProjectRequest;
import com.management.projects.dto.response.ProjectResponse;
import com.management.projects.repository.BoardRepository;
import com.management.projects.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public ProjectResponse createProject(ProjectRequest request){
        Board board = boardRepository.findBoardById(new ObjectId(request.getBoardId()));
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setProjectManager(userRepository.findByEmail(request.getManager().getEmail()));
        List<Project> projects = board.getProjects();
        projects.add(project);
        board.setProjects(projects);
        boardRepository.save(board);
        return createProjectResponse(request.getBoardId(), project);
    }

    public List<ProjectResponse> loadAllProjectsFromBoard(Board board) {
        return null;
    }


    private ProjectResponse createProjectResponse(String boardId, Project project){
        return ProjectResponse
                .builder()
                .boardId(boardId)
                .projectId(project.getId().toString())
                .name(project.getName())
                .description(project.getDescription())
                .manager(new NameEmail(
                        project.getProjectManager().getName(),
                        project.getProjectManager().getEmail())
                )
                //.activities(activityService.loadAllActivitiesFromProject(project))
                //.collaborators(loadAllCollaboratorsFromPoject(boardId))
                .build();
    }

}
