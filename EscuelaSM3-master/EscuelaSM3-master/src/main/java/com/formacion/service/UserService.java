package com.formacion.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.formacion.entities.User;
import com.formacion.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Gaurda el usuario con el rol de Usuario por defecto
     * @param user
     * @return
     */
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Asignar un rol por defecto si no se proporciona
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER"); // Rol predeterminado
        }
        return userRepository.save(user);
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public Long countUsers() {
        return userRepository.count();
    }
    
    /**
     * Verifica que la contraseña del formulario coincide con la hasheada
     * @param username
     * @param rawPassword
     * @return
     */
    public boolean verifyUserCredentials(String username, String rawPassword) {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            // Compara la contraseña ingresada con la encriptada almacenada
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }
    
    public String registerUser(String email, String rawPassword) {
        // Evita registros duplicados por email
        if (email == null || email.isBlank()) {
            return "El email no puede estar vacío.";
        }
        if (rawPassword == null || rawPassword.isBlank()) {
            return "La contraseña no puede estar vacía.";
        }

        // Comprobamos si ya existe el usuario
        if (userRepository.findByEmail(email) != null) {
            return "El usuario " + email + " ya está registrado";
        }

        // Creamos y guardamos el nuevo usuario
        User user = new User();
        user.setEmail(email);
        user.setPassword(rawPassword); // Será codificado en saveUser
        saveUser(user);

        return "El usuario " + email + " ha sido creado";
    }


    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Este metodo viene implicito de UserDetailsService
     * Spring Security lo usa internamente para gestionar usuarios y permisos 
     */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }
        return user;

	}

    
}