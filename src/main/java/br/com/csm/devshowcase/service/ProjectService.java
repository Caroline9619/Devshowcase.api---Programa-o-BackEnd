package br.com.csm.devshowcase.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.csm.devshowcase.dto.ProjectRequestDTO;
import br.com.csm.devshowcase.dto.ProjectResponseDTO;
import br.com.csm.devshowcase.model.Project;
import br.com.csm.devshowcase.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProjectResponseDTO salvar(ProjectRequestDTO dto) {

        Project project = new Project(
                dto.title(),
                dto.description(),
                dto.repositoryUrl());

        project = repository.save(project);

        return ProjectResponseDTO.fromEntity(project);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> buscarTodos() {

        return repository.findAll()
                .stream()
                .map(ProjectResponseDTO::fromEntity)
                .toList();
    }
}