package com.formacion.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
	
	private List<User> users;
	
	public List<User> getUsers(){
		return users;
	}

	@Data
	public static class User{
		private Long id;
		private String firstName;
	    private String lastName;
	    private String maidenName;
	    private int age;
	    private String gender;
	    private String email;
	    private String phone;
	    private String username;
	    private String password;
	    private String birthDate;
	    private String image;
	    private String bloodGroup;
	    private double height;
	    private double weight;
	    private String eyeColor;
	    private String ip;
	    private String macAddress;
	    private String university;
	    private String ein;
	    private String ssn;
	    private String userAgent;
	    private String role;

	    @Embedded
	    private Hair hair;

	    @Embedded
	    private Address address;

	    @Embedded
	    private Company company;

	    @Embedded
	    private Bank bank;

	    @Embedded
	    private Crypto crypto;
	}
	
}
