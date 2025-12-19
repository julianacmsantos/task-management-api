package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.model.TaskStatus;

public class TaskResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private TaskStatus status;
    private LocalDateTime createdAt;

    // Construtor vazio
    public TaskResponse() {}

    // Construtor completo
    public TaskResponse(Long id, String titulo, String descricao, 
                        TaskStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}