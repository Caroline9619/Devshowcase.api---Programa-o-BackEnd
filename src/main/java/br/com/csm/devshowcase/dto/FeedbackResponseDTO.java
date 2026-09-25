package br.com.csm.devshowcase.dto;

import br.com.csm.devshowcase.model.Feedback;

public record FeedbackResponseDTO(

        Long id,
        Integer rating,
        String comment

) {

    public static FeedbackResponseDTO fromEntity(Feedback feedback) {

        return new FeedbackResponseDTO(
                feedback.getId(),
                feedback.getRating(),
                feedback.getComment()
        );
    }
}