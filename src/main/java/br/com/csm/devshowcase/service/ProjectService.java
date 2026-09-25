package br.com.csm.devshowcase.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.csm.devshowcase.dto.ProjectRequestDTO;
import br.com.csm.devshowcase.dto.ProjectResponseDTO;
import br.com.csm.devshowcase.model.Project;
import br.com.csm.devshowcase.repository.ProjectRepository;
import br.com.csm.devshowcase.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    @Transactional
public ProjectResponseDTO incrementarCurtida(Long id) {

    Project project = repository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Projeto não encontrado"));

    project.setLikes(project.getLikes() + 1);

    project = repository.save(project);

    return ProjectResponseDTO.fromEntity(project);
}
@Transactional(readOnly = true)
public Page<ProjectResponseDTO> buscarComFiltro(
        String technology,
        Integer page,
        Integer size) {

    Pageable pageable = PageRequest.of(page, size);

    Page<Project> projects;

    if (technology == null || technology.isBlank()) {

        projects = repository.findAll(pageable);

    } else {

        projects = repository.findByTechnologiesNameIgnoreCase(
                technology,
                pageable);
    }

    return projects.map(ProjectResponseDTO::fromEntity);
}
}