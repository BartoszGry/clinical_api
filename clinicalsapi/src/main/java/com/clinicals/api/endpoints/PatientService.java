package com.clinicals.api.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.clinicals.api.model.Patient;
import com.clinicals.api.repos.PatientRepo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;


@Consumes("application/json")
@Produces("application/json")
@Path("/api")
public class PatientService {

	@Autowired
	PatientRepo repo;
	
	@Path("/patients")
	@POST
	public Patient createPatient(Patient patient){
		return repo.save(patient);
	}
	
	@Path("/patients/{id}")
	@GET
	public Patient getPatient(@PathParam("id") int id) {
		return repo.findById(id).get();
	}
	
	@Path("/patients")
	@GET
	public List<Patient> getPatients(){
		return repo.findAll();
	}
	
}
