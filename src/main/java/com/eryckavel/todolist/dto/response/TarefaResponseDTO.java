package com.eryckavel.todolist.dto.response;

import com.eryckavel.todolist.enums.PrioridadeTarefa;
import com.eryckavel.todolist.enums.StatusTarefa;
import com.eryckavel.todolist.model.Tarefa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TarefaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private PrioridadeTarefa prioridade;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLimite;
    private Long idUsuario;
    private Long idCategoria;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criadoEm;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime atualizadoEm;

    public TarefaResponseDTO() {
    }

    public TarefaResponseDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.status = tarefa.getStatus();
        this.prioridade = tarefa.getPrioridade();
        this.dataLimite = tarefa.getDataLimite();
        this.idUsuario = tarefa.getUsuario() != null ? tarefa.getUsuario().getId() : null;
        this.idCategoria = tarefa.getCategoria() != null ? tarefa.getCategoria().getId() : null;
        this.criadoEm = tarefa.getCriadoEm();
        this.atualizadoEm = tarefa.getAtualizadoEm();
    }

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

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    public PrioridadeTarefa getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeTarefa prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
