package com.management.projects.domain;

import com.management.projects.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "boards")
public class Board {

    @Id
    private ObjectId id;
    private String publicId;
    private String name;
    @DocumentReference
    private User boardOwner;
    @DocumentReference
    private List<User> collaborators;
    private List<Project> projects;

    public Board(String name, User boardOwner) {
        this.name = name;
        this.boardOwner = boardOwner;
    }

}
