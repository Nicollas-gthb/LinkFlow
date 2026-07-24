package com.linkflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record IntegrationRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String name,

        @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
        String description,

        @NotBlank(message = "Url de origem é obrigatória")
        @URL(message = "URL de origem inválida")
        String sourceUrl,

        @NotBlank(message = "Url de destino é obrigatória")
        @URL(message = "URL de destino inválida")
        String targetUrl,

        String secret
) {
}
