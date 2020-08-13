package com.infy.user.service;

import java.util.List;

import com.infy.user.model.Login;
import com.infy.user.model.PatientRegistrationDTO;
import com.infy.user.model.PatientRegistrationDetail;

public interface PatientRegistrationService {
	public String addPatient(PatientRegistrationDTO patientRegistrationDTO) throws Exception ;
	public PatientRegistrationDTO getPatientById(String patientId) throws Exception ;
	public PatientRegistrationDTO getPatientByNameAndmobileNumberAndEmailAddress(String userName,String emailAddress,Long mobileNumber) throws Exception;
	public String updatePatientRegistrationDetail(PatientRegistrationDetail patientRegistrationDetail,String patientId) throws Exception ;
	public PatientRegistrationDTO getPatient(Login login)  throws Exception;
	public String updatePatientWithPhysicalDetail(PatientRegistrationDTO PatientRegistrationDTO) throws Exception ;
	public List<PatientRegistrationDTO> getPatientByDoctorId(String doctorId) throws Exception;
	public List<PatientRegistrationDTO> getAllPatient() throws Exception;
}
