package com.management.projects.repository;

import com.management.projects.domain.Board;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends MongoRepository<Board, ObjectId> {

    Board findBoardById(ObjectId id);

}
