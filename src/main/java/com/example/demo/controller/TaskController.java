package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;  

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskMapper;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    private final TaskMapper mapper;

    public TaskController(TaskService service, TaskMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> criar(@Valid @RequestBody TaskRequest request) {
        Long tempId = System.currentTimeMillis(); // ID temporário
        Task task = service.criarTarefa(tempId, request.getTitulo(), request.getDescricao());
        TaskResponse response = mapper.toResponse(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);    
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> listar() {
        List<Task> tasks = service.listarTarefas();
        List<TaskResponse> response = tasks.stream()
            .map(mapper::toResponse) // chama mapper.toResponse para cada task
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);    
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> buscar(@PathVariable Long id) {
        Task task = service.buscarTarefaPorId(id);
        TaskResponse response = mapper.toResponse(task);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<TaskResponse> concluir(@PathVariable Long id) {
        Task task = service.concluirTarefa(id);
        TaskResponse response = mapper.toResponse(task);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> excluir(@PathVariable Long id) {
            service.excluirTarefa(id);
            return ResponseEntity.noContent().build();
        }
    }
