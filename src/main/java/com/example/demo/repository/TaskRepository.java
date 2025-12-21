package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Task;

@Repository
public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public Task save(Task task) {
        tasks.add(task);
        return task;
    }

    public List<Task> findAll() {
        return tasks;
    }

    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public void delete(Task task) {
        tasks.remove(task);
    }
    
}
