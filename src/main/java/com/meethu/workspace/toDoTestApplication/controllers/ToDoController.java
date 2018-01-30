package com.meethu.workspace.toDoTestApplication.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meethu.workspace.model.ToDo;
import com.meethu.workspace.toDoTestApplication.repository.ToDoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ToDoController {

	@Autowired
	ToDoRepository toDorepository;
	
	@GetMapping("/todos")
	public List<ToDo> getAllToDos(){
		   Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdDate");
	        return toDorepository.findAll(sortByCreatedAtDesc);
	}
	@PostMapping("/todos")
    public ToDo createTodo(@Valid @RequestBody ToDo todo) {
        todo.setIsCompleted(false);
        return toDorepository.save(todo);
    }
	
	@GetMapping(value="/todos/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable("id") String id) {
        ToDo todo = toDorepository.findOne(id);
        if(todo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        }
    }
	
	@DeleteMapping(value="/todos/{id}")
    public void deleteTodo(@PathVariable("id") String id) {
        toDorepository.delete(id);
    }
        @PutMapping(value="/todos/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody ToDo todo) {
        ToDo todoData = toDorepository.findOne(id);
        if(todoData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoData.setTaskName(todo.getTaskName());
        todoData.setIsCompleted(todo.getIsCompleted());
        ToDo updatedTodo = toDorepository.save(todoData);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
}
