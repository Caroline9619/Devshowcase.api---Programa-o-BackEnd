package br.com.csm.devshowcase.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.csm.devshowcase.dto.ProjectRequestDTO;
import br.com.csm.devshowcase.dto.ProjectResponseDTO;
import br.com.csm.devshowcase.service.ProjectService;
import jakarta.validation.Valid;
import br.com.csm.devshowcase.dto.FeedbackRequestDTO;
import br.com.csm.devshowcase.dto.FeedbackResponseDTO;
import br.com.csm.devshowcase.service.FeedbackService;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;
    private final FeedbackService feedbackService;

   public ProjectController(
        ProjectService service,
        FeedbackService feedbackService) {

    this.service = service;
    this.feedbackService = feedbackService;
}

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> cadastrar(
            @Valid @RequestBody ProjectRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.salvar(dto));
    }

    @GetMapping
public ResponseEntity<Page<ProjectResponseDTO>> buscarTodos(

        @RequestParam(required = false) String technology,

        @RequestParam(defaultValue = "0") Integer page,

        @RequestParam(defaultValue = "10") Integer size) {

    return ResponseEntity.ok(

            service.buscarComFiltro(

                    technology,

                    page,

                    size));
}
    @PostMapping("/{id}/feedbacks")
public ResponseEntity<FeedbackResponseDTO> cadastrarFeedback(

        @PathVariable Long id,
        @Valid @RequestBody FeedbackRequestDTO dto) {

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(feedbackService.salvar(id, dto));
}
@PutMapping("/{id}/upvote")
public ResponseEntity<ProjectResponseDTO> upvote(
        @PathVariable Long id) {

    return ResponseEntity.ok(service.incrementarCurtida(id));
}
}