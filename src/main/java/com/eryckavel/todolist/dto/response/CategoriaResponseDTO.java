package com.eryckavel.todolist.dto.response;

import com.eryckavel.todolist.model.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CategoriaResponseDTO(
        Long id,
        String nome,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime criadoEm
) {

    public CategoriaResponseDTO(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getCriadoEm()
        );
    }

}
