package com.example.demo.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task criarTarefa(String titulo, String descricao) {
        Long novoId = idGenerator.getAndIncrement();
        Task task = new Task(novoId, titulo, descricao);
        return repository.save(task);
    }

    public List<Task> listarTarefas() {
        return repository.findAll();
    }

    public Task buscarTarefaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
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
