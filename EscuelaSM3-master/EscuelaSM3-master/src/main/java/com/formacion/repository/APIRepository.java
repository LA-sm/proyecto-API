package com.formacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacion.entities.API;

public interface APIRepository extends JpaRepository<API, Long> {
    boolean existsByFirstNameAndLastNameAndImage(String firstName, String lastName, String image);
}

