package com.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.formacion.entities.User;
import com.formacion.repository.UserRepositorio;

@Service
public class UserService implements UserDetailsService{
    private final UserRepositorio userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepositorio userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


  
    public User findUserByUsername(String email) {
        return userRepository.findByEmail(email); // Consulta a la base de datos
    }

 // Método para encontrar el usuario por ID
    public User findUserById(int id) {
        return userRepository.findById((long) id).orElse(null); // Devuelve el usuario o null si no existe
    }
    
    public void saveUser(User user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Asignar un rol por defecto si no se proporciona
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER"); // Rol predeterminado
        }
    	userRepository.save(user); // Guardar el usuario en la base de datos
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public Long countUsers() {
        return userRepository.count();
    }
    
    public boolean verifyUserCredentials(String email, String rawPassword) {
    	
        User user = userRepository.findByEmail(email);
          
        if (user != null) {
            // Usar la instancia de passwordEncoder para verificar la contraseña
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }

        return false;
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
