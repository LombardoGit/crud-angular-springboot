package com.cjava.apirestmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@Document(collection = "alumno")
public class Alumno {
	@Id 
	private  String id;
	private  String nombre;
	private  String apellido;
	private  String curso;
	private int edad;
}