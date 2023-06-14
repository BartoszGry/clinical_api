package com.clinicals.api.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.clinicals.api.model.ClinicalData;
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
	public Patient createPatient(Patient patient) {
		return repo.save(patient);
	}

	@Path("/patients/{id}")
	@GET
	public Patient getPatient(@PathParam("id") int id) {
		return repo.findById(id).get();
	}

	@Path("/patients")
	@GET
	public List<Patient> getPatients() {
		return repo.findAll();
	}

	@Path("/patients/analyze/{id}")
	@GET
	public Patient analyze(@PathParam("id") int id) {
		Patient patient = repo.findById(id).get();
		List<ClinicalData> clinicalData = new ArrayList<>(patient.getClinicalData());
		for (ClinicalData eachEntry : clinicalData) {
			if (eachEntry.getComponentName().equals("hw")) {
				String[] heightAndWeight = eachEntry.getComponentValue().split("/");
			float height = Float.parseFloat(heightAndWeight[0]);
			float weight = Float.parseFloat(heightAndWeight[1]);
			
			float bmi = weight/(height*height)*10000;
			ClinicalData bmiData = new ClinicalData();
			bmiData.setComponentName("bmi");
			bmiData.setComponentValue(String.valueOf(bmi));
			patient.getClinicalData().add(bmiData);
			
			}
		}
		return patient;
	}

}
