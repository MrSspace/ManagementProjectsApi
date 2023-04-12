package com.management.projects.service;

import com.management.projects.domain.Board;
import com.management.projects.domain.WorkAssignmentKey;
import com.management.projects.dto.AssignmentDTO;
import com.management.projects.dto.NameEmail;
import com.management.projects.dto.NameId;
import com.management.projects.dto.response.BoardResponse;
import com.management.projects.repository.BoardRepository;
import com.management.projects.repository.UserRepository;
import com.management.projects.role.Permission;
import com.management.projects.user.User;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final WorkAssignmentKeyService keyService;
    private final ProjectService projectService;


    public BoardResponse createBoard(NameEmail request){
        User user = userRepository.findByEmail(request.getEmail());
        Board board = new Board(request.getName(), user);
        boardRepository.insert(board);
        keyService.assignKey(board,null,null,null,user,Permission.BOARD_OWNER);
        userRepository.save(user);
        return createBoardResponse(board);
    }

    public void addCollaboratorToBoard(AssignmentDTO request){
        User user = userRepository.findByEmail(request.getEmail());
        Board board = boardRepository.findBoardById(new ObjectId(request.getBoardId()));
        keyService.assignKey(board,null,null,null, user, Permission.COLLABORATOR);
        List<User> collaborators = board.getCollaborators();
        collaborators.add(user);
        board.setCollaborators(collaborators);
        boardRepository.save(board);
    }

    public void removeCollaboratorFromBoard(AssignmentDTO request){
        User user = userRepository.findByEmail(request.getEmail());
        Board board = boardRepository.findBoardById(new ObjectId(request.getBoardId()));
        List<User> currentCollaborators = board.getCollaborators();

        List<User> modifiedCollaborators = new ArrayList<>();

        for (User collaborator : currentCollaborators) {
            if ( !collaborator.getId().equals(user.getId()) ) {
                modifiedCollaborators.add(collaborator);
            }
        }

        board.setCollaborators(modifiedCollaborators);
        boardRepository.save(board);
    }

    public BoardResponse loadBoard(ObjectId id){
        Board board = boardRepository.findBoardById(id);
        return createBoardResponse(board);
    }

    public void changeBoardName(NameId request){
        Board board = boardRepository.findBoardById(new ObjectId(request.getId()));
        board.setName(request.getName());
        boardRepository.save(board);
    }

    public void deleteBoard(NameId request){
        Board board = boardRepository.findBoardById(new ObjectId(request.getId()));
        boardRepository.delete(board);
    }

    public List<NameEmail> loadAllCollaboratorsFromBoard(ObjectId boardId){
        Board board = boardRepository.findBoardById(boardId);
        List<User> users = board.getCollaborators();
        List<NameEmail> collaborators = new ArrayList<>();
        for(User user : users){
            collaborators.add(new NameEmail(user.getName(), user.getEmail()));
        }
        return collaborators;
    }

    public List<BoardResponse> loadAllBoardsByUser(User user){
        List<ObjectId> boardsIds = new ArrayList<>();
        List<WorkAssignmentKey> assigns = user.getWorkAssignmentKeys();
        for(WorkAssignmentKey key : assigns){
            if ( !assigns.contains( key.getBoardId() ) ) {
                boardsIds.add(key.getBoardId());
            }
        }
        return boardsIds.stream().map(this::loadBoard).collect(Collectors.toList());
    }

    private BoardResponse createBoardResponse(Board board){
        return BoardResponse
                .builder()
                .id(board.getId().toString())
                .name(board.getName())
                .owner(new NameEmail(
                        board.getBoardOwner().getName(),
                        board.getBoardOwner().getEmail())
                )
                .projects(projectService.loadAllProjectsFromBoard(board))
                .collaborators(loadAllCollaboratorsFromBoard(board.getId()))
                .build();
    }

}
