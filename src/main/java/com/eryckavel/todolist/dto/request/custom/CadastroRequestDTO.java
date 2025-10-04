package com.eryckavel.todolist.dto.request.custom;

import jakarta.validation.constraints.NotBlank;

public record CadastroRequestDTO(
        @NotBlank(message = "Campo obrigatório!") String nome,
        @NotBlank(message = "Campo obrigatório!") String email,
        @NotBlank(message = "Campo obrigatório!") String login,
        @NotBlank(message = "Campo obrigatório!") String senha) {
}
