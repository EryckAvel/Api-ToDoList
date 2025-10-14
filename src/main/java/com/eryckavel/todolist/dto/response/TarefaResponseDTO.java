package com.eryckavel.todolist.dto.response;

import com.eryckavel.todolist.enums.PrioridadeTarefa;
import com.eryckavel.todolist.enums.StatusTarefa;
import com.eryckavel.todolist.model.Tarefa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        StatusTarefa status,
        PrioridadeTarefa prioridade,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataLimite,
        Long idUsuario,
        Long idCategoria,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime criadoEm,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime atualizadoEm
) {

    public TarefaResponseDTO(Tarefa tarefa) {
        this (
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getPrioridade(),
                tarefa.getDataLimite(),
                tarefa.getUsuario() != null ? tarefa.getUsuario().getId() : null,
                tarefa.getCategoria() != null ? tarefa.getCategoria().getId() : null,
                tarefa.getCriadoEm(),
                tarefa.getAtualizadoEm()
        );
    }

}
