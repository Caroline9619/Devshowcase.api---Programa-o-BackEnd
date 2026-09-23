package br.com.csm.devshowcase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProjectRequestDTO(

    @NotBlank(message = "Título obrigatório")
    String title,

    @NotBlank(message = "Descrição obrigatória")
    String description,

    @Pattern(
        regexp = "^(http|https).*$",
        message = "URL inválida"
    )
    String repositoryUrl

) {}