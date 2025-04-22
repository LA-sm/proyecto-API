package com.formacion.controlador.res;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.formacion.service.ApiService;

@Controller
@RequestMapping("/api")
public class APIController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/data")
    public String fetchApiData(Model model) {
        // Primero, aseguramos que los datos estén actualizados en la base de datos
        apiService.fetchAndSaveApiData();  // Esto consumirá la API y guardará los datos

        // Ahora, obtenemos los datos almacenados en la base de datos
        model.addAttribute("lista", apiService.getApiDataList());
        
        // Devolvemos la vista 'principal' con los datos
        return "principal";
    }
}
