package com.infy.user.entities;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.infy.user.model.CovidData;
import com.infy.user.model.PatientPhysicalAndMedicalDetails;
import com.infy.user.model.PatientRegistrationDetail;

@Document(collection="PatientRegistrationService")
public class PatientRegistration {
	@Transient
	private static final String Sequence="Registration_Sequence";
	@Id
	private String patientId;
	
	private PatientRegistrationDetail patientRegistrationDetail;
	private PatientPhysicalAndMedicalDetails patientPhysicalAndMedicalDetails;
	@DBRef
	private DoctorDetail doctorDetail;
	private ArrayList<CovidData> covidDataList;
	public DoctorDetail getDoctorDetail() {
		return doctorDetail;
	}
	public void setDoctorDetail(DoctorDetail doctorDetail) {
		this.doctorDetail = doctorDetail;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public PatientRegistrationDetail getPatientRegistrationDetail() {
		return patientRegistrationDetail;
	}
	public void setPatientRegistrationDetail(PatientRegistrationDetail patientRegistrationDetail) {
		this.patientRegistrationDetail = patientRegistrationDetail;
	}
	public PatientPhysicalAndMedicalDetails getPatientPhysicalAndMedicalDetails() {
		return patientPhysicalAndMedicalDetails;
	}
	public void setPatientPhysicalAndMedicalDetails(PatientPhysicalAndMedicalDetails patientPhysicalAndMedicalDetails) {
		this.patientPhysicalAndMedicalDetails = patientPhysicalAndMedicalDetails;
	}
	public static String getSequence() {
		return Sequence;
	}
	public ArrayList<CovidData> getCovidDataList() {
		return covidDataList;
	}
	public void setCovidDataList(ArrayList<CovidData> covidDataList) {
		this.covidDataList = covidDataList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patientId == null) ? 0 : patientId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientRegistration other = (PatientRegistration) obj;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		return true;
	}
	
}
