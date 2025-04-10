package com.formacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.java.Log;

@Configuration
@Log
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers("/anonimo/**").permitAll() //Permite paso a cualquier clase del controlador principal
//                		.requestMatchers("/admin/**").hasRole("ADMIN") // Sólo usuarios con rol ADMIN
                        .requestMatchers("/api/**", "/usuario/**").authenticated() // Paso a usuarios autenticados
//                        .requestMatchers("/usuario/**").hasAnyRole("USER", "ADMIN") // Paso a controlador para usuarios con roles USER o ADMIN 
                		.anyRequest().permitAll()
                )
                .formLogin(form -> form
                	    .loginPage("/anonimo/login")
                	    .loginProcessingUrl("/anonimo/formularioLogin")
                	    .usernameParameter("email") // ¡Esto es clave!
                	    .passwordParameter("password")
                	    .failureUrl("/anonimo/login?error=true")
                	    .defaultSuccessUrl("/api/data", true)
                	    .permitAll()
                	)
                .logout(logout -> logout
                	    .logoutUrl("/logout")
                	    .logoutSuccessHandler((request, response, authentication) -> {
                            // Lógica personalizada después del logout
                            log.info("Usuario cerró sesión: " + (authentication != null ? authentication.getName() : "Anonimo"));
                            response.sendRedirect("/anonimo/"); // Redirección a la raiz
                        })

                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
                .build();
	}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Configura el codificador de contraseñas
    }
}
