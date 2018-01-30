package com.meethu.workspace.toDoTestApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.meethu.workspace.model.ToDo;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {

}
