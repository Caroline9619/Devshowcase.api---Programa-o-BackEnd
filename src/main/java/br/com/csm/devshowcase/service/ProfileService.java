package br.com.csm.devshowcase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.csm.devshowcase.dto.ProfileRequestDTO;
import br.com.csm.devshowcase.dto.ProfileResponseDTO;
import br.com.csm.devshowcase.exception.ResourceNotFoundException;
import br.com.csm.devshowcase.model.Profile;
import br.com.csm.devshowcase.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    @Transactional
    public ProfileResponseDTO salvar(ProfileRequestDTO dto) {

        Profile profile = new Profile(
                dto.name(),
                dto.email(),
                dto.bio());

        profile = repository.save(profile);

        return ProfileResponseDTO.fromEntity(profile);
    }

    @Transactional(readOnly = true)
    public ProfileResponseDTO buscarPorId(Long id) {

        Profile profile = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Perfil não encontrado"));

        return ProfileResponseDTO.fromEntity(profile);
    }
}