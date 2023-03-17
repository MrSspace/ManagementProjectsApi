package com.management.projects.domain;

import com.management.projects.user.User;

import java.util.List;

public class Board {

    private String id;
    private String name;
    private User boardOwner;
    private List<User> collaborators;
    private List<Project> projects;

}
