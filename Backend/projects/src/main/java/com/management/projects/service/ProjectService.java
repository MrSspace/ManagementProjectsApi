package com.management.projects.service;

import com.management.projects.domain.Board;
import com.management.projects.domain.Project;
import com.management.projects.domain.WorkAssignmentKey;
import com.management.projects.dto.AssignmentDTO;
import com.management.projects.dto.NameEmail;
import com.management.projects.dto.request.ProjectRequest;
import com.management.projects.dto.response.ProjectResponse;
import com.management.projects.repository.BoardRepository;
import com.management.projects.repository.UserRepository;
import com.management.projects.role.Permission;
import com.management.projects.user.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private static final int FIRST_PROJECT_ID = 1;

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final  WorkAssignmentKeyService keyService;

    public ProjectResponse createProject(ProjectRequest request){
        ObjectId boardId = new ObjectId(request.getBoardId());
        User user = userRepository.findByEmail(request.getManager().getEmail());

        Board board = boardRepository.findBoardById(boardId);
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

        keyService.projectAssign(board, project, user, Permission.PROJECT_MANAGER);
        boardRepository.save(board);
        return createProjectResponse(request.getBoardId(), project);
    }

    private void setProject(Board board, Project project, List<Project> projects){
        project.setId(FIRST_PROJECT_ID + projects.size());
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

    public ProjectResponse loadProject(String ... ids){
        ObjectId boardId = new ObjectId(ids[0]);
        Integer projectId = Integer.parseInt(ids[1]);
        Board board = boardRepository.findBoardById(boardId);
        Project project = getProject(board, projectId);
        return createProjectResponse(boardId.toString(), project);
    }

    private Project getProject(Board board, Integer projectId){
        return board.getProjects()
                .stream()
                .filter(inListProject -> projectId == inListProject.getId())
                .collect(Collectors.toList())
                .get(0);
    }

    public void addCollaboratorToProject(AssignmentDTO request){
        User user = userRepository.findByEmail(request.getEmail());
        Board board = boardRepository.findBoardById(new ObjectId(request.getBoardId()));
        Integer projectId = Integer.parseInt(request.getProjectId());
        Project project = getProject(board, projectId);

        keyService.projectAssign(board, project, user, Permission.COLLABORATOR);
        List<User> collaborators = project.getCollaborators();
        collaborators.add(user);

        List<Project> projects = board.getProjects();
        for(Project proj : projects) {
            if (proj.getId() == projectId){
                proj.setCollaborators(collaborators);
            }
        }
        board.setProjects(projects);

        boardRepository.save(board);
    }

    public void removeCollaboratorToProject(AssignmentDTO request) {
        ObjectId boardId = new ObjectId(request.getBoardId());
        Integer projectId = Integer.parseInt(request.getProjectId());

        User user = userRepository.findByEmail(request.getEmail());
        Board board = boardRepository.findBoardById(boardId);
        Project project = board.getProjects().get(projectId - FIRST_PROJECT_ID);

        List<Project> projects = board.getProjects();
        List<User> currentCollaborators = project.getCollaborators();
        List<User> modifiedCollaborators = new ArrayList<>();

        for (User collaborator : currentCollaborators) {
            if ( !collaborator.getId().equals(user.getId()) ) {
                modifiedCollaborators.add(collaborator);
            }
        }

        project.setCollaborators(modifiedCollaborators);

        for (Project proj : projects) {
            if(proj.getId() == project.getId()){
                proj.setCollaborators(project.getCollaborators());
            }
        }

        WorkAssignmentKey key = new WorkAssignmentKey(boardId,Permission.COLLABORATOR);
        key.setProjectId(projectId);

        List<WorkAssignmentKey> assignmentKeys = user.getWorkAssignmentKeys().stream().filter(
                assignmentKey -> !keyService.isTheAssignmentKey(assignmentKey,key)
        ).collect(Collectors.toList());

        user.setWorkAssignmentKeys(assignmentKeys);
        board.setProjects(projects);

        userRepository.save(user);
        boardRepository.save(board);
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
