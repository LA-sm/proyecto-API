package com.formacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formacion.entities.User;
import com.formacion.service.UserService;

import lombok.extern.java.Log;

/**
 * Controller lo usaremos para movernos entre vistas (HTML, JSP...) 
 * 
 */
@Controller
@Log
@RequestMapping("/anonimo") //Extension para comunicarnos con este controlador (Más facil de controlar)
public class Controlador {
	
	@Autowired
    private UserService userService;

	
	/**
	 * Solicitud GET, en este caso sera nuestra ruta raiz (localhost:8080/)
	 * segun arranquemos encontrara la ruta / recogera los datos en el modelo y redirigira a la vista Index
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String inicio(Model model) {
		log.info("Arranca la app");
		//model se encarga de mandar los datos en formato clave valor
		model.addAttribute("texto", "Texto de ejemplo");
		//Devolvemos el nombre de la vista
		return "index";
	}
	
	/**
	 * Solicitud GET, en este caso sera nuestra ruta raiz (localhost:8080/index)
	 * Esto es un problema ya que no podemos dejar que se acceda libremente a cualquier ruta
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String inicioDesdeIndex(Model model) {
		log.info("Llamamos a index");
		model.addAttribute("texto", "Mas tarde habra que encargarse de capar esto");
		//Devolvemos el nombre de la vista 
		return "index";
	}
	
	/**
	 * Recibe el dato del formulario y lo reenvia de vuelta a index
	 * @param dato
	 * @param model
	 * @return
	 */
	@PostMapping("/formularioRegistro")
	public String procesarFormularioRegistro(String email, String password, Model model) {
	    log.info("Procesamos el formulario de Registro");
	    log.info("Usuario " + email);

	    String mensaje;

	    if (email == null || email.isBlank()) {
	        mensaje = "El campo email no puede estar vacío";
	    } else {
	        User existente = userService.findUserByUsername(email);
	        if (existente == null) {
	            User nuevoUsuario = new User();
	            nuevoUsuario.setEmail(email);
	            nuevoUsuario.setPassword(password);
	            userService.saveUser(nuevoUsuario);
	            mensaje = "El usuario " + email + " ha sido creado correctamente";
	        } else {
	            mensaje = "El usuario " + email + " ya está registrado";
	        }
	    }

	    model.addAttribute("user", email);
	    model.addAttribute("password", password);
	    model.addAttribute("mensaje", mensaje);
	    return "index";
	}

	
	
	
	/**
	 * Hemos simplificado su uso, Spring security hara la gestion
	 * @param model
	 * @param error
	 * @param logout
	 * @return
	 */
	@PostMapping("/formularioLogin")
	public String procesarFormularioLogin(Model model, String error, String logout) {
	    log.info("Procesamos el formulario de Login con Spring Security");
	    
	    if (error != null) {
	        model.addAttribute("mensaje", "Usuario o contraseña incorrectos");
	        log.info("Error en autenticación.");
	    }
	    if (logout != null) {
	        model.addAttribute("mensaje", "Sesión cerrada exitosamente.");
	        log.info("Usuario ha cerrado sesión.");
	    }
	    
	    return "login"; // Retorna la vista de login.jsp
	}

	/**
	 * Switch de vistas de Registo - Login
	 * @param source
	 * @return
	 */
	@GetMapping("/loginRegistro")
    public String switchFormulario(String source) {
		log.info("Cambio a formulario de " + source);
		return source.equalsIgnoreCase("registro")?"login":"index";
    }
	
	/**
	 * Control el caso de error al logar (Spring security)
	 * @param model
	 * @param error
	 * @param logout
	 * @return
	 */
	@GetMapping("/login")
    public String loginPage(Model model, String error, String logout) {
		log.info("Login error:" + error + " Logout: " + logout);
        if (error != null) {
            model.addAttribute("mensaje", "Usuario o contraseña incorrectos");
        }
        if (logout != null) {
            model.addAttribute("mensaje", "Has cerrado sesión exitosamente");
        }
        return "login"; // Retorna la vista login.jsp
    }
}
