package com.management.projects.domain;

import com.management.projects.user.User;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.List;

@Data
public class Activity {

    @Id
    private int id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @DocumentReference
    private User taskManager;
    @DocumentReference
    private List<User> collaborators;
    private List<Task> tasks;

}
