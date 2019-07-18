package com.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Task;
import com.boot.repository.TaskRepository;

@RestController
@RequestMapping("")
@CrossOrigin("*")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;
	
	//@GetMapping("/tasks")
	@RequestMapping(value = "tasks", method = RequestMethod.GET)
    public List<Task> getAllTasks() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return taskRepository.findAll(sortByCreatedAtDesc);
    }

    //@PostMapping("/tasks")
	@RequestMapping(value = "tasks", method = RequestMethod.POST)
    public Task createTask(@Valid @RequestBody Task task) {
        // task.setCompleted(false);
        return taskRepository.save(task);
    }

    //@GetMapping(value="/tasks/{id}")
	@RequestMapping(value = "tasks/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTodoById(@PathVariable("id") String id) {
        return taskRepository.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    //@PutMapping(value="/tasks/{id}")
	@RequestMapping(value = "tasks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody Task task) {
        return taskRepository.findById(id)
                .map(taskData -> {
                	taskData.setName(task.getName());
                	taskData.setAuthor(task.getAuthor());
                	Task updatedTask = taskRepository.save(taskData);
                    return ResponseEntity.ok().body(updatedTask);
                }).orElse(ResponseEntity.notFound().build());
    }

    //@DeleteMapping(value="/tasks/{id}")
	@RequestMapping(value = "tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
        return taskRepository.findById(id)
                .map(task -> {
                	taskRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
