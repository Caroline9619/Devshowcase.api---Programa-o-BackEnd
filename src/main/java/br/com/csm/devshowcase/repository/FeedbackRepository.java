package br.com.csm.devshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csm.devshowcase.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}