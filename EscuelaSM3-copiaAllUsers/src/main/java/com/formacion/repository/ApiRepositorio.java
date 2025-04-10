package com.formacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.formacion.entities.Api;

@Repository
public interface ApiRepositorio extends JpaRepository<Api, Long>{
	boolean existsByFirstNameAndLastName(String firstName, String lastName);

}
