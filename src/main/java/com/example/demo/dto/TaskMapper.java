package com.example.demo.dto;

import org.springframework.stereotype.Component;

import com.example.demo.model.Task;

@Component
public class TaskMapper {

    // Converte Task (entidade interna) → TaskResponse (resposta ao cliente)
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitulo(),
            task.getDescricao(),
            task.getStatus(),
            task.getCreatedAt()
        );
    }

    // Converte TaskRequest (dados do cliente) → Task (entidade interna)
    public Task toEntity(TaskRequest request, Long id) {
        return new Task(id, request.getTitulo(), request.getDescricao());
    }
}