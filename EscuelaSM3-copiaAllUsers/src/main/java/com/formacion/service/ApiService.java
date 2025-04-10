package com.formacion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formacion.entities.Api;
//import com.formacion.controller.Controlador;
import com.formacion.entities.UserData;
import com.formacion.repository.ApiRepositorio;

import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Log
public class ApiService {

	@Autowired
	private ApiRepositorio apiRepositorio;

	private final WebClient webClient;
	private final ObjectMapper objectMapper; // Añadimos ObjectMapper para mapear JSON a objeto

	public ApiService(WebClient webClient, ObjectMapper objectMapper, ApiRepositorio apiRepositorio) {
		this.webClient = webClient;
		this.objectMapper = new ObjectMapper();
		this.apiRepositorio = apiRepositorio;
	}

	public void fetchAndSaveApiData() {
		String url = "https://dummyjson.com/users"; // URL de la API
		String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block(); // Bloquea para obtener
																								// el resultado (no
																								// reactivo)

		if (response.isEmpty()) {
			log.info("No muestra valores de API");
		} else {
			log.info(response);// Imprime la respuesta de la API para depuración
		}

		try {
			UserData userData = objectMapper.readValue(response, UserData.class);

			if (userData.getUsers() != null) {
				for (UserData.User user : userData.getUsers()) {
					
					boolean exists = apiRepositorio.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName());
					
					if (!exists) {
						Api api = new Api();
						api.setFirstName(user.getFirstName());
						api.setLastName(user.getLastName());
						api.setMaidenName(user.getMaidenName());
						api.setAge(user.getAge());
						api.setGender(user.getGender());
						api.setEmail(user.getEmail());
						api.setPhone(user.getPhone());
						api.setUsername(user.getUsername());
						api.setPassword(user.getPassword());
						api.setBirthDate(user.getBirthDate());
						api.setImage(user.getImage());
						api.setBloodGroup(user.getBloodGroup());
						api.setHeight(user.getHeight());
						api.setWeight(user.getWeight());
						api.setEyeColor(user.getEyeColor());
						api.setIp(user.getIp());
						api.setMacAddress(user.getMacAddress());
						api.setUniversity(user.getUniversity());
						api.setEin(user.getEin());
						api.setSsn(user.getSsn());
						api.setUserAgent(user.getUserAgent());
						api.setRole(user.getRole());
						api.setHair(user.getHair());
						api.setAddress(user.getAddress());
						api.setCompany(user.getCompany());
						api.setBank(user.getBank());
						api.setCrypto(user.getCrypto());

						apiRepositorio.save(api);
					}
					
				}
			}
		} catch (JsonMappingException e) {
			log.severe("Error al procesar los datos JSON: " + e.getMessage());
		} catch (JsonProcessingException e) {
			log.severe("Error al procesar los datos JSON: " + e.getMessage());
		} catch (ObjectOptimisticLockingFailureException e) {
			log.severe("Conflicto de actualización");
		}

	}

	public void fetchAndSaveSingleUser() {
		String url = "https://dummyjson.com/users/1"; // URL de la API
		String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block(); // Bloquea para obtener
																								// el resultado (no
																								// reactivo)

		if (response.isEmpty()) {
			log.info("No muestra valores de API");
		} else {
			log.info(response);// Imprime la respuesta de la API para depuración
		}

		try {
			UserData.User user = objectMapper.readValue(response, UserData.User.class);

			if (user != null) {
				boolean exists = apiRepositorio.existsByFirstNameAndLastName(user.getFirstName(), user.getLastName());
				
				if (!exists) {
					Api api = new Api();
					api.setFirstName(user.getFirstName());
					api.setLastName(user.getLastName());
					api.setMaidenName(user.getMaidenName());
					api.setAge(user.getAge());
					api.setGender(user.getGender());
					api.setEmail(user.getEmail());
					api.setPhone(user.getPhone());
					api.setUsername(user.getUsername());
					api.setPassword(user.getPassword());
					api.setBirthDate(user.getBirthDate());
					api.setImage(user.getImage());
					api.setBloodGroup(user.getBloodGroup());
					api.setHeight(user.getHeight());
					api.setWeight(user.getWeight());
					api.setEyeColor(user.getEyeColor());
					api.setIp(user.getIp());
					api.setMacAddress(user.getMacAddress());
					api.setUniversity(user.getUniversity());
					api.setEin(user.getEin());
					api.setSsn(user.getSsn());
					api.setUserAgent(user.getUserAgent());
					api.setRole(user.getRole());
					api.setHair(user.getHair());
					api.setAddress(user.getAddress());
					api.setCompany(user.getCompany());
					api.setBank(user.getBank());
					api.setCrypto(user.getCrypto());

					apiRepositorio.save(api);
					log.info("Usuario guardado correctamente");
				}
					
			}
		} catch (JsonMappingException e) {
			log.severe("Error al procesar los datos JSON: " + e.getMessage());
		} catch (JsonProcessingException e) {
			log.severe("Error al procesar los datos JSON: " + e.getMessage());
		} catch (ObjectOptimisticLockingFailureException e) {
			log.severe("Conflicto de actualización");
		}

	}

	public List<Api> getApiDataList() {
		return apiRepositorio.findAll();
	}

}
