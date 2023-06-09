package com.clinicals.api.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ClinicalData {

	@Id
	private int id;
	private String componentName;
	private String componentValue;
	private Timestamp mesureDateTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id",nullable=false)
	private Patient patient;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}

	public Timestamp getMesureDateTime() {
		return mesureDateTime;
	}

	public void setMesureDateTime(Timestamp mesureDateTime) {
		this.mesureDateTime = mesureDateTime;
	}
}
