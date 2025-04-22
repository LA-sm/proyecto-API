package com.formacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.formacion.service.UserService;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/anonimo")
public class Controlador {

    @Autowired
    private UserService userService;

    // Página de inicio (root)
    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Arranca la app");
        model.addAttribute("texto", "Texto de ejemplo");
        return "index";
    }

    // Acceso a través de /index
    @GetMapping("/index")
    public String inicioDesdeIndex(Model model) {
        log.info("Llamamos a index");
        model.addAttribute("texto", "Más tarde habrá que encargarse de capar esto");
        return "index";
    }

    // Registro de usuario
    @PostMapping("/formularioRegistro")
    public String procesarFormularioRegistro( String user,  String password, Model model) {
        log.info("Procesamos el formulario de Registro para: " + user);
        String mensaje = userService.registerUser(user, password);
        model.addAttribute("user", user);
        model.addAttribute("password", password);
        model.addAttribute("mensaje", mensaje);
        return "index";
    }

    // Login gestionado por Spring Security
    @PostMapping("/formularioLogin")
    public String procesarFormularioLogin(Model model,
                                          @RequestParam(required = false) String error,
                                          @RequestParam(required = false) String logout) {
        log.info("Procesamos el formulario de Login");
        if (error != null) {
            model.addAttribute("mensaje", "Usuario o contraseña incorrectos");
            log.warning("Error de autenticación.");
        }
        if (logout != null) {
            model.addAttribute("mensaje", "Sesión cerrada exitosamente.");
            log.info("Usuario cerró sesión.");
        }
        return "login";
    }

    // Cambio entre formularios de login y registro
    @GetMapping("/loginRegistro")
    public String switchFormulario(@RequestParam String source) {
        log.info("Cambio de vista a: " + source);
        return source.equalsIgnoreCase("registro") ? "login" : "index";
    }

    // Página de login controlada por Spring Security
    @GetMapping("/login")
    public String loginPage(Model model,
                            @RequestParam(required = false) String error,
                            @RequestParam(required = false) String logout) {
        log.info("Acceso a login. Error: " + error + ", Logout: " + logout);
        if (error != null) {
            model.addAttribute("mensaje", "Usuario o contraseña incorrectos");
        }
        if (logout != null) {
            model.addAttribute("mensaje", "Has cerrado sesión exitosamente");
        }
        return "login";
    }
}
