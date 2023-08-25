package com.speech.cardatabase.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProviderRespository extends JpaRepository<Provider, Long> {

    int aaa= 0;

}
