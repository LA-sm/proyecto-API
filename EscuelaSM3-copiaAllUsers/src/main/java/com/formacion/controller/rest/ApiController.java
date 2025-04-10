package com.formacion.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.formacion.service.ApiService;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
    private ApiService apiService;
	
    @GetMapping("/data")
    public String fetchApiData(Model model) {
    	
    	apiService.fetchAndSaveApiData();
    	
    	//apiService.fetchAndSaveSingleUser();
    	
    	model.addAttribute("lista", apiService.getApiDataList());
        return "principal";
    }

}
