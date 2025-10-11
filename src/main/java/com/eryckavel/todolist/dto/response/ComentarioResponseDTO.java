package com.eryckavel.todolist.dto.response;

import com.eryckavel.todolist.model.Comentario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ComentarioResponseDTO(Long id, Long idUsuario, Long idTarefa, String conteudo,@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime criadoEm) {

    public ComentarioResponseDTO(Comentario comentario) {
        this(
            comentario.getId(),
            comentario.getUsuario() != null ? comentario.getUsuario().getId() : null,
            comentario.getTarefa() != null ? comentario.getTarefa().getId() : null,
            comentario.getConteudo(),
            comentario.getCriadoEm()
        );
    }

}

