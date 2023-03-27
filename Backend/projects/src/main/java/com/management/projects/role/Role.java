package com.management.projects.role;

public enum Role {
    ADMIN("admin"), USER("user");

    private String name;

    private Role(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
