package com.formacion.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
	    private String department;
	    private String name;
	    private String title;
}
