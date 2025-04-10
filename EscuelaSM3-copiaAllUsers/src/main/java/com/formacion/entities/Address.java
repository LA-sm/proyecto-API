package com.formacion.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Embeddable
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    private String address;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private String country;

}
