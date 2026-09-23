package br.com.csm.devshowcase.dto;

import br.com.csm.devshowcase.model.Profile;

public record ProfileResponseDTO(

        Long id,
        String name,
        String email,
        String bio

) {

    public static ProfileResponseDTO fromEntity(Profile profile) {

        return new ProfileResponseDTO(
                profile.getId(),
                profile.getName(),
                profile.getEmail(),
                profile.getBio()
        );
    }
}