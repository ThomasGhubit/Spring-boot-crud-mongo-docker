package com.boot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
	
}