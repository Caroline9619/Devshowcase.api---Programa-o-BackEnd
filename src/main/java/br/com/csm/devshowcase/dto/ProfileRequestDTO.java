package br.com.csm.devshowcase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfileRequestDTO(

        @NotBlank(message = "Nome obrigatório")
        String name,

        @Email(message = "Email inválido")
        String email,

        String bio

) {
}