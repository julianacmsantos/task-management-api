package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
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
        return taks;
    }

    public Task findById(Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void delete(Task task) {
        tasks.remove(task);
    }
    
}
