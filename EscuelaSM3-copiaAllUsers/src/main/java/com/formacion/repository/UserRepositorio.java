package com.formacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formacion.entities.User;

@Repository
public interface UserRepositorio extends JpaRepository<User, Long> {
	
    User findByEmail(String email);// Buscar cliente por su email
    
}