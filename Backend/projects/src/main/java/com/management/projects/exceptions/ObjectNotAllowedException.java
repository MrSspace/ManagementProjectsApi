package com.management.projects.exceptions;

import lombok.Data;

@Data
public class ObjectNotAllowedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String description;

    public ObjectNotAllowedException(String description){
        super(String.format("Object type isn't allowed. %s", description));
        this.description = description;
    }

}
