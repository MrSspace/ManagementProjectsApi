package com.management.projects.service;

import com.management.projects.domain.Board;
import com.management.projects.domain.WorkAssignmentKey;
import com.management.projects.dto.NameEmail;
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


    public BoardResponse createBoard(NameEmail request){
        User user = userRepository.findByEmail(request.getEmail());
        Board board = new Board(request.getName(), user);
        boardRepository.insert(board);

        WorkAssignmentKey workKey = new WorkAssignmentKey();
        workKey.setBoardId(board.getId());
        workKey.setPermission(Permission.BOARD_OWNER);

        List<WorkAssignmentKey> workAssignments = new ArrayList<>();
        workAssignments.add(workKey);
        user.setWorkAssignmentKeys(workAssignments);
        userRepository.save(user);

        return BoardResponse
                .builder()
                .name(board.getName())
                .owner( user.getName() )
                .build();
    }

    public BoardResponse addProjectToBoard(){
        return null;
    }

    public void removeProjectFromBoard(){}

    public BoardResponse addCollaboratorToBoard(){
        return null;
    }

    public void removeCollaboratorFromBoard(){}

    public BoardResponse loadBoard(ObjectId id){
        Board board = boardRepository.findBoardById(id);
        return BoardResponse
                .builder()
                .name(board.getName())
                .owner(board.getBoardOwner().getName())
                //.projects(projectService.loadAllProjectsFromBoard(board))
                .build();
    }

    public void updateBoard(){}

    public void deleteBoard(){}

    public List<User> loadAllUsersByBoard(Board board){
        return null;
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

}
