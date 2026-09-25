package br.com.csm.devshowcase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.csm.devshowcase.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByTechnologiesNameIgnoreCase(String technology, Pageable pageable);

}