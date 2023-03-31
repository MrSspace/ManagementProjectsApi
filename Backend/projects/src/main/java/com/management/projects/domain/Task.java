package com.management.projects.domain;

import com.management.projects.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @DocumentReference
    private User taskManager;

}
