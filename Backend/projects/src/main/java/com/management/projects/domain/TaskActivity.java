package com.management.projects.domain;

import com.management.projects.user.User;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Data
public class TaskActivity {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    @DocumentReference
    private User activityManager;

}
