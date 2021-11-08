package com.cjava.apirestmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cjava.apirestmongodb.model.Alumno;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {
}
