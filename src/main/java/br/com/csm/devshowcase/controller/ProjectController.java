package br.com.csm.devshowcase.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.csm.devshowcase.dto.ProjectRequestDTO;
import br.com.csm.devshowcase.dto.ProjectResponseDTO;
import br.com.csm.devshowcase.service.ProjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> cadastrar(
            @Valid @RequestBody ProjectRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> buscarTodos() {

        return ResponseEntity.ok(service.buscarTodos());
    }
}