package br.com.csm.devshowcase.dto;

import jakarta.validation.constraints.NotBlank;

public record TechnologyRequestDTO(

    @NotBlank(message = "Nome da tecnologia é obrigatório")
    String name

) {}