package com.formacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacion.entities.API;
import com.formacion.entities.UserApiResponse;
import com.formacion.repository.APIRepository;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    private APIRepository apiRepository;

    @Autowired
    private RestTemplate restTemplate;  // Usamos RestTemplate para consumir la API

    private static final String API_URL = "https://dummyjson.com/users";

    // Método para obtener los datos de la API externa y guardarlos en la base de datos
    public void fetchAndSaveApiData() {
        UserApiResponse response = restTemplate.getForObject(API_URL, UserApiResponse.class);

        if (response != null && response.getUsers() != null) {
            for (UserApiResponse.User user : response.getUsers()) {
                // Verificamos si ya existe para evitar duplicados
                boolean exists = apiRepository.existsByFirstNameAndLastNameAndImage(
                        user.getFirstName(), user.getLastName(), user.getImage()
                );

                if (!exists) {
                    API api = new API();
                    api.setFirstName(user.getFirstName());
                    api.setLastName(user.getLastName());
                    api.setImage(user.getImage());
                    apiRepository.save(api);
                }
            }
        }
    }


    // Método para obtener los datos almacenados en la base de datos
    public List<API> getApiDataList() {
        return apiRepository.findAll();
    }
}
