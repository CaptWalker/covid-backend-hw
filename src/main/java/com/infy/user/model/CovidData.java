package com.infy.user.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CovidData {
	@Transient
	private String patientId;
	@Transient
	private static final  String sequence= "COVID_DATA_ID";
	private Character bcgVaccine;
	private Character fluVaccine;
	private List<String> specimenType;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd",timezone="IST")
	private Date collectionDate;
	private Long sampleId;
	private String patientCategory;
	private String immunoCompromisedCondition;
	private String covidTestResult;
	public Character getBcgVaccine() {
		return bcgVaccine;
	}
	public void setBcgVaccine(Character bcgVaccine) {
		this.bcgVaccine = bcgVaccine;
	}
	public Character getFluVaccine() {
		return fluVaccine;
	}
	public void setFluVaccine(Character fluVaccine) {
		this.fluVaccine = fluVaccine;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public List<String> getSpecimenType() {
		return specimenType;
	}
	public void setSpecimenType(List<String> specimenType) {
		this.specimenType = specimenType;
	}
	public Long getSampleId() {
		return sampleId;
	}
	public void setSampleId(Long sampleId) {
		this.sampleId = sampleId;
	}
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	public String getPatientCategory() {
		return patientCategory;
	}
	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}
	public String getImmunoCompromisedCondition() {
		return immunoCompromisedCondition;
	}
	public void setImmunoCompromisedCondition(String immunoCompromisedCondition) {
		this.immunoCompromisedCondition = immunoCompromisedCondition;
	}
	public String getCovidTestResult() {
		return covidTestResult;
	}
	public void setCovidTestResult(String covidTestResult) {
		this.covidTestResult = covidTestResult;
	}
	public static String getSequence() {
		return sequence;
	}
	
}
