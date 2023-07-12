package com.speech.cardatabase.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface OwnerRepository extends CrudRepository<Owner, Long> {

        //Optional<Owner> findByFirstName(String firstName);
}
