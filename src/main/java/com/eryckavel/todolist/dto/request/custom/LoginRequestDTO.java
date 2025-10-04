package com.eryckavel.todolist.dto.request.custom;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank(message = "Campo obrigatório!") String login,
                              @NotBlank(message = "Campo obrigatório!") String senha) {
}
