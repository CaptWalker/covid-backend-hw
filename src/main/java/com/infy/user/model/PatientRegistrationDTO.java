package com.infy.user.model;

import java.util.ArrayList;

import com.infy.user.entities.PatientRegistration;

public class PatientRegistrationDTO {
	private String patientId;
	private PatientRegistrationDetail patientRegistrationDetail;
	private PatientPhysicalAndMedicalDetails patientPhysicalAndMedicalDetails;
	private DoctorDetailDTO doctorDetailDTO;
	private ArrayList<CovidData> covidDataList;
	public DoctorDetailDTO getDoctorDetailDTO() {
		return doctorDetailDTO;
	}
	public void setDoctorDetailDTO(DoctorDetailDTO doctorDetailDTO) {
		this.doctorDetailDTO = doctorDetailDTO;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public PatientPhysicalAndMedicalDetails getPatientPhysicalAndMedicalDetails() {
		return patientPhysicalAndMedicalDetails;
	}
	public void setPatientPhysicalAndMedicalDetails(PatientPhysicalAndMedicalDetails patientPhysicalAndMedicalDetails) {
		this.patientPhysicalAndMedicalDetails = patientPhysicalAndMedicalDetails;
	}
	
	public PatientRegistrationDetail getPatientRegistrationDetail() {
		return patientRegistrationDetail;
	}
	public void setPatientRegistrationDetail(PatientRegistrationDetail patientRegistrationDetail) {
		this.patientRegistrationDetail = patientRegistrationDetail;
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
		PatientRegistrationDTO other = (PatientRegistrationDTO) obj;
		if (patientId == null) {
			if (other.patientId != null)
				return false;
		} else if (!patientId.equals(other.patientId))
			return false;
		return true;
	}
	public static PatientRegistration patientRegistrationDTOConvertPatientRegistration(PatientRegistrationDTO patientRegistrationDTO) {
		PatientRegistration patientRegistration = new PatientRegistration();
		patientRegistration.setPatientId(patientRegistrationDTO.getPatientId());
		patientRegistration.setPatientPhysicalAndMedicalDetails(patientRegistrationDTO.getPatientPhysicalAndMedicalDetails());
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDTO.getPatientRegistrationDetail());
		patientRegistration.setCovidDataList(patientRegistrationDTO.getCovidDataList());
		if(patientRegistrationDTO.getDoctorDetailDTO()!=null)
		patientRegistration.setDoctorDetail(DoctorDetailDTO.doctorDetailDTOConvertDoctorDetail(patientRegistrationDTO.getDoctorDetailDTO()));
		return patientRegistration;
	}
	public static PatientRegistrationDTO patientRegistrationConvertPatientRegistrationDTO(PatientRegistration patientRegistration) {
		PatientRegistrationDTO patientRegistrationDTO = new PatientRegistrationDTO();
		patientRegistrationDTO.setPatientId(patientRegistration.getPatientId());
		patientRegistrationDTO.setPatientPhysicalAndMedicalDetails(patientRegistration.getPatientPhysicalAndMedicalDetails());
		patientRegistrationDTO.setPatientRegistrationDetail(patientRegistration.getPatientRegistrationDetail());
		patientRegistrationDTO.setCovidDataList(patientRegistration.getCovidDataList());
		if(patientRegistration.getDoctorDetail()!=null)
		patientRegistrationDTO.setDoctorDetailDTO(DoctorDetailDTO.doctorDetailConvertDoctorDetailDTO(patientRegistration.getDoctorDetail()));
		return patientRegistrationDTO;
	}
}
