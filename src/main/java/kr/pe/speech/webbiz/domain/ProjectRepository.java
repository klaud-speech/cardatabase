package kr.pe.speech.webbiz.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Optional<Project> findByProjectname (String projectname );
}
