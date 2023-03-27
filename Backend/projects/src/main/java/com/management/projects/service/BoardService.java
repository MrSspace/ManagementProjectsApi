package com.management.projects.service;

import com.management.projects.domain.Board;
import com.management.projects.domain.WorkAssignmentKey;
import com.management.projects.dto.request.CreateBoardRequest;
import com.management.projects.dto.response.BoardResponse;
import com.management.projects.repository.BoardRepository;
import com.management.projects.repository.UserRepository;
import com.management.projects.role.Permission;
import com.management.projects.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public BoardResponse createBoard(CreateBoardRequest request){
        User user = userRepository.findByEmail(request.getUserEmail());
        Board board = new Board(request.getBoardName(), user);
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
                .boardName(board.getName())
                .boardOwner( user.getName() )
                .build();
    }

    public BoardResponse loadBoard(){
        return null;
    }
    public BoardResponse addProjectToBoard(){
        return null;
    }
    public void updateBoardName(){}
    public void deleteBoard(){}
    public List<User> loadAllUsersByBoard(Board board){
        return null;
    }

}
