package com.clinicals.api.endpoints;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinicals.api.dto.ClinicalDataRequest;
import com.clinicals.api.model.ClinicalData;
import com.clinicals.api.model.Patient;
import com.clinicals.api.repos.ClinicalDataRepo;
import com.clinicals.api.repos.PatientRepo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;


@Consumes("application/json")
@Produces("application/json")
@Path("/api")
@CrossOriginResourceSharing(allowAllOrigins = true)
public class ClinicalDataService {

	@Autowired
	PatientRepo patientRepo;
	@Autowired
	ClinicalDataRepo clinicalDataRepo;

	@Path("/clinicals")
	@POST
	public ClinicalData saveClinicalData(ClinicalDataRequest request) {
		Patient patient = patientRepo.findById(request.getPatientId()).get();
		ClinicalData clinicalData = new ClinicalData();
		clinicalData.setPatient(patient);
		clinicalData.setComponentName(request.getComponentName());
		clinicalData.setComponentValue(request.getComponentValue());
		
		
		return clinicalDataRepo.save(clinicalData);
	}
}
