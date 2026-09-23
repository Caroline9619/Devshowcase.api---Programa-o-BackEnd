package br.com.csm.devshowcase.dto;

import br.com.csm.devshowcase.model.Project;

public record ProjectResponseDTO(

    Long id,
    String title,
    String description,
    String repositoryUrl

) {

    public static ProjectResponseDTO fromEntity(Project project) {

        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getRepositoryUrl()
        );
    }
}