package kr.pe.speech.webbiz.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OwnerRepository extends CrudRepository<Owner, Long> {

        //Optional<Owner> findByFirstName(String firstName);
}
