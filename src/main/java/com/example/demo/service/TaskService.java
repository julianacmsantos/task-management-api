package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.Role;
import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task criarTarefa(String titulo, String descricao) {
        User currentUser = getCurrentUser();
        Task task = new Task(titulo, descricao, currentUser);
        return repository.save(task);
    }

    public List<Task> listarTarefas() {
        User currentUser = getCurrentUser();

        if (isAdmin()) {
            return repository.findAll();
        }

        return repository.findAll().stream()
                .filter(task -> task.getOwner().getId().equals(currentUser.getId()))
                .toList();
    }

    public Task buscarTarefaPorId(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (!isAdmin() && !task.getOwner().getId().equals(getCurrentUser().getId())) {
            throw new UnauthorizedException("Você não tem permissão para acessar esta tarefa");
        }

        return task;
    }

    public Task concluirTarefa(Long id) {
        Task task = buscarTarefaPorId(id);
        task.setStatus(TaskStatus.DONE);
        return repository.save(task);
    }

    public void excluirTarefa(Long id) {
        Task task = buscarTarefaPorId(id);
        repository.delete(task);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    private boolean isAdmin() {
        User currentUser = getCurrentUser();
        return currentUser.getRole() == Role.ADMIN;
    }
}
