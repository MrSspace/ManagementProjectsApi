package com.management.projects.service;

import com.management.projects.dto.request.AuthRequest;
import com.management.projects.dto.request.RegisterRequest;
import com.management.projects.dto.request.UpdateNameOfUserRequest;
import com.management.projects.dto.response.*;
import com.management.projects.exception.InvalidPasswordException;
import com.management.projects.repository.UserRepository;
import com.management.projects.role.Role;
import com.management.projects.user.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public void createUser(RegisterRequest request, Role role){
        User user = new User(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));
        user.setRoles(Collections.singletonList(role));
        userRepository.insert(user);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserResponse updateNameOfUser(UpdateNameOfUserRequest request){
        User user = userRepository.findByEmail(request.getEmail());
        user.setName(request.getName());
        userRepository.save(user);
        return new UserResponse(user.getName(), user.getEmail());
    }

    public void updatePassword(AuthRequest request){
        User user = userRepository.findByEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(AuthRequest request){
        User user = getUserByEmail(request.getEmail());
        if ( !encoder.matches(request.getPassword(), user.getPassword()) ){
            throw new InvalidPasswordException();
        }
        userRepository.delete(user);
    }

    public void assignBoard(){}

    public BoardResponse loadBoards(){
        return null;
    }

    public void assignProject(){}

    public ProjectResponse loadProjects(){
        return null;
    }

    public void assignActivity(){}

    public ActivityResponse loadActivities(){
        return null;
    }

    public void assignTask(){}

    public TaskResponse loadTasks(){
        return null;
    }

    public void deleteAssign(ObjectId userId, ObjectId boardId){}

}
