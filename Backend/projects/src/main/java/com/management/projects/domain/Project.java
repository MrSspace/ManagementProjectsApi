package com.management.projects.domain;

import com.management.projects.user.User;

import java.util.Date;
import java.util.List;

public class Project {

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private User projectManager;
    private List<User> collaborators;
    private List<Task> tasks;

}
