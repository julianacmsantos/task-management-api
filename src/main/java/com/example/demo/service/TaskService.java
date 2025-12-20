package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task criarTarefa(Long id, String titulo, String descricao) {
        Task task = new Task(id, titulo, descricao);
        return repository.save(task);
    }

    public List<Task> listarTarefas() {
        return repository.findAll();
    }

    public Task buscarTarefaPorId(Long id) {
        Task task = repository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    public Task concluirTarefa(Long id) {
        Task task = buscarTarefaPorId(id);
        task.setStatus(TaskStatus.DONE);
        return task;
    }

    public void excluirTarefa(Long id) {
        Task task = buscarTarefaPorId(id);
        repository.delete(task);
    }

}
