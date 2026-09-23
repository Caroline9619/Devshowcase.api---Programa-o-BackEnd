package br.com.csm.devshowcase.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.csm.devshowcase.dto.TechnologyRequestDTO;
import br.com.csm.devshowcase.dto.TechnologyResponseDTO;
import br.com.csm.devshowcase.model.Technology;
import br.com.csm.devshowcase.repository.TechnologyRepository;

@Service
public class TechnologyService {

    private final TechnologyRepository repository;

    public TechnologyService(TechnologyRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TechnologyResponseDTO salvar(TechnologyRequestDTO dto) {

        Technology technology = new Technology(dto.name());

        technology = repository.save(technology);

        return TechnologyResponseDTO.fromEntity(technology);
    }

    @Transactional(readOnly = true)
    public List<TechnologyResponseDTO> buscarTodos() {

        return repository.findAll()
                .stream()
                .map(TechnologyResponseDTO::fromEntity)
                .toList();
    }
}