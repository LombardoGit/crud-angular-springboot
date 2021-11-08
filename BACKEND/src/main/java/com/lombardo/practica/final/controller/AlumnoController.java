package com.cjava.apirestmongodb.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cjava.apirestmongodb.model.Alumno;
import com.cjava.apirestmongodb.repository.AlumnoRepository;

@RestController
public class AlumnoController
{
	@Autowired
	AlumnoRepository alumnoRepository;

	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"todoOk\" : true }";
	}

	@GetMapping("/alumno")
	public List<Alumno> getAlumnos()
	{
		List<Alumno> alumnoList = alumnoRepository.findAll();
		return alumnoList;
	}

	@GetMapping("/alumno/{id}")
	public Optional<Alumno> getAlumno(@PathVariable String id)
	{
		Optional<Alumno> alm = alumnoRepository.findById(id);
		return alm;
	}

	@PutMapping("/alumno/{id}")
	public Optional<Alumno> updateAlumno(@RequestBody Alumno newAlumno, @PathVariable String id)
	{
		Optional<Alumno> optionalAlm = alumnoRepository.findById(id);
		if (optionalAlm.isPresent()) {
			Alumno alm = optionalAlm.get();
			alm.setNombre(newAlumno.getNombre());
			alm.setApellido(newAlumno.getApellido());
			alm.setCurso(newAlumno.getCurso());
			alm.setEdad(newAlumno.getEdad());
			alumnoRepository.save(alm);
		}
		return optionalAlm;
	}

	@DeleteMapping(value = "/alumno/{id}", produces = "application/json; charset=utf-8")
	public String deleteAlumno(@PathVariable String id) {
		Boolean result = alumnoRepository.existsById(id);
		alumnoRepository.deleteById(id);
		return "{ \"operacionExitosa\" : "+ (result ? "true" : "false") +" }";
	}

	@PostMapping("/alumno")
	public Alumno addAlumno(@RequestBody Alumno newAlumno)
	{
		String id = String.valueOf(new Random().nextInt());
		Alumno alm = new Alumno(id, newAlumno.getNombre(), newAlumno.getApellido(), newAlumno.getCurso(),newAlumno.getEdad());
		alumnoRepository.insert(alm);
		return alm;
	}
}
