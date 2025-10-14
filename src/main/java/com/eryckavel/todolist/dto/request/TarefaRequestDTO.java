package com.eryckavel.todolist.dto.request;

import com.eryckavel.todolist.enums.PrioridadeTarefa;
import com.eryckavel.todolist.enums.StatusTarefa;

import java.time.LocalDate;

public record TarefaRequestDTO(
        String titulo,
        String descricao,
        StatusTarefa status,
        PrioridadeTarefa prioridade,
        LocalDate dataLimite,
        Long idUsuario,
        Long idCategoria
) {

}
