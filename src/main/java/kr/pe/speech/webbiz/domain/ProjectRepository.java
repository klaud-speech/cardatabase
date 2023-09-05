package kr.pe.speech.webbiz.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectName (String projectName );

    Optional<Project> findByUrlName(String urlName);

    @Query(nativeQuery = true, value = "SELECT * FROM webbiz.project WHERE  project.user_id = :uid ORDER BY updated_at desc LIMIT 1" )
    List<Project> findLatestProjectUser(@Param("uid") Long user_id );
}
