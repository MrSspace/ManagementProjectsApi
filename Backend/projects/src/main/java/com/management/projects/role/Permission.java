package com.management.projects.role;

public enum Permission {
    BOARD_OWNER("Board_Owner"), PROJECT_MANAGER("Project_Manager"),
    TASK_MANAGER("Task_Manager"), COLLABORATOR("Collaborator");

    private String name;

    private Permission(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
