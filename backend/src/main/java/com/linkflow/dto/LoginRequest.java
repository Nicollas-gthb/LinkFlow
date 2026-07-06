package com.linkflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Email é obrigatorio")
        @Email(message = "Email invalido")
        String email,

        @NotBlank(message = "Senha é obrigatoria")
        String password
) {
}
