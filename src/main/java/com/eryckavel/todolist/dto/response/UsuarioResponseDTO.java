package com.eryckavel.todolist.dto.response;

import com.eryckavel.todolist.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        String senha,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime criadoEm
) {

    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getCriadoEm()
        );
    }

}
