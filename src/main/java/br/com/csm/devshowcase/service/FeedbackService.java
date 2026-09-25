package br.com.csm.devshowcase.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.csm.devshowcase.dto.FeedbackRequestDTO;
import br.com.csm.devshowcase.dto.FeedbackResponseDTO;
import br.com.csm.devshowcase.exception.ResourceNotFoundException;
import br.com.csm.devshowcase.model.Feedback;
import br.com.csm.devshowcase.model.Project;
import br.com.csm.devshowcase.repository.FeedbackRepository;
import br.com.csm.devshowcase.repository.ProjectRepository;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            ProjectRepository projectRepository) {

        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public FeedbackResponseDTO salvar(Long projectId, FeedbackRequestDTO dto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Projeto não encontrado"));

        Feedback feedback = new Feedback(
                dto.rating(),
                dto.comment(),
                project);

        feedback = feedbackRepository.save(feedback);

        atualizarMedia(project);

        return FeedbackResponseDTO.fromEntity(feedback);
    }

    private void atualizarMedia(Project project) {

        List<Feedback> feedbacks = project.getFeedbacks();

        double media = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        project.setAverageRating(media);

        projectRepository.save(project);
    }
}