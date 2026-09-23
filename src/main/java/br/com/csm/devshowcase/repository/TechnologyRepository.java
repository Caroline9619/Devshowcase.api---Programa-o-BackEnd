package br.com.csm.devshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.csm.devshowcase.model.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}