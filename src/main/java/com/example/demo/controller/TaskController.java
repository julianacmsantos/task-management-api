package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task criar(@RequestParam Long id,
                      @RequestParam String titulo,
                      @RequestParam String descricao) {
        return service.criarTarefa(id, titulo, descricao);
    }

    @GetMapping
    public List<Task> listar() {
        return service.listarTarefas();
    }

    @GetMapping("/{id}")
    public Task buscar(@PathVariable Long id) {
        return service.buscarTarefaPorId(id);
    }

    @PutMapping("/{id}/concluir")
    public Task concluir(@PathVariable Long id) {
        return service.concluirTarefa(id);
    }

    @DeleteMapping("/{id}")
        public void excluir(@PathVariable Long id) {
            service.excluirTarefa(id);
        }
    }
