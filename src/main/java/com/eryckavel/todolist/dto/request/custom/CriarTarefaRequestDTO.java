package com.eryckavel.todolist.dto.request.custom;

import com.eryckavel.todolist.enums.PrioridadeTarefa;
import com.eryckavel.todolist.enums.StatusTarefa;

import java.time.LocalDate;

public record CriarTarefaRequestDTO(
        String titulo,
        String descricao,
        StatusTarefa status,
        PrioridadeTarefa prioridade,
        LocalDate dataLimite,
        Long idCategoria
) {
}
