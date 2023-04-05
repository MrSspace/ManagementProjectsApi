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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private static final int FIRST = 1;

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public ProjectResponse createProject(ProjectRequest request){
        Board board = boardRepository.findBoardById(new ObjectId(request.getBoardId()));
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setProjectManager(userRepository.findByEmail(request.getManager().getEmail()));
        if (board.getProjects() == null) {
            List<Project> projects = new ArrayList<>();
            setProject(board, project, projects);
        } else if (board.getProjects() != null) {
            List<Project> projects = board.getProjects();
            setProject(board, project, projects);
        }
        boardRepository.save(board);
        return createProjectResponse(request.getBoardId(), project);
    }

    private void setProject(Board board, Project project, List<Project> projects){
        project.setId(FIRST + projects.size());
        projects.add(project);
        board.setProjects(projects);
    }

    public List<ProjectResponse> loadAllProjectsFromBoard(Board board) {
        List<ProjectResponse> projectsResponse = new ArrayList<>();
        List<Project> projects = boardRepository.findBoardById(board.getId()).getProjects();
        if (projects == null) {
            return null;
        }
        for(Project project : projects) {
            ProjectResponse response = createProjectResponse(board.getId().toString(),project);
            projectsResponse.add(response);
        }
        return projectsResponse;
    }


    private ProjectResponse createProjectResponse(String boardId, Project project){
        return ProjectResponse
                .builder()
                .boardId(boardId)
                .projectId(Integer.toString(project.getId()))
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
