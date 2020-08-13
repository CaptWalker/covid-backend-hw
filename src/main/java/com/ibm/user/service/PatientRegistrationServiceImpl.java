package com.infy.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.user.entities.PatientRegistration;
import com.infy.user.model.DoctorDetailDTO;
import com.infy.user.model.Login;
import com.infy.user.model.PatientRegistrationDTO;
import com.infy.user.model.PatientRegistrationDetail;
import com.infy.user.repository.PatientRegistrationRepository;

@Service
public class PatientRegistrationServiceImpl implements PatientRegistrationService{
	@Autowired
	private PatientRegistrationRepository patientRegistrationRepository;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@Autowired
	private DoctorDetailServiceImpl doctorDetailServiceImpl;
	public String addPatient(PatientRegistrationDTO patientRegistrationDTO) throws Exception {
		patientRegistrationDTO.getPatientRegistrationDetail().setDateOfRegistration(new Date());
		PatientRegistration patientRegistration= PatientRegistrationDTO.patientRegistrationDTOConvertPatientRegistration(patientRegistrationDTO);
		if(patientRegistration.getDoctorDetail()!=null) {
			doctorDetailServiceImpl.getDoctorById(patientRegistration.getDoctorDetail().getDoctorId());
		}
		patientRegistration.setPatientId("P"+sequenceGeneratorService.generateSequence(PatientRegistration.getSequence()));
		patientRegistration= patientRegistrationRepository.save(patientRegistration);
		return patientRegistration.getPatientId();
	}
	public PatientRegistrationDTO getPatientById(String patientId) throws Exception {
		PatientRegistration patientRegistration=patientRegistrationRepository.findById(patientId).orElseThrow(()-> new Exception("UserRegistrationService.NO_USER_FOUND"));
		return PatientRegistrationDTO.patientRegistrationConvertPatientRegistrationDTO(patientRegistration);
	}
	public PatientRegistrationDTO getPatientByNameAndmobileNumberAndEmailAddress(String userName,String emailAddress,Long mobileNumber) throws Exception {
		PatientRegistration patientRegistration=patientRegistrationRepository.findByUserNameandMobileNumberandEmailAddress(userName,mobileNumber,emailAddress).orElseThrow(()-> new Exception("UserRegistrationService.NO_USER_FOUND"));
		return PatientRegistrationDTO.patientRegistrationConvertPatientRegistrationDTO(patientRegistration);
	}
	public List<PatientRegistrationDTO> getPatientByDoctorId(String doctorId) throws Exception {
		List<PatientRegistration> patientRegistrations= patientRegistrationRepository.findByDoctorId(doctorId);
		if(patientRegistrations.isEmpty())
			throw new Exception("No patient allocated to Doctor");
		List<PatientRegistrationDTO> pateintRegistartionDTOs = new ArrayList<>();
		for (PatientRegistration patientRegistration : patientRegistrations) {
			pateintRegistartionDTOs.add(PatientRegistrationDTO.patientRegistrationConvertPatientRegistrationDTO(patientRegistration));
		}
		return pateintRegistartionDTOs;
		
	}
	public List<PatientRegistrationDTO> getAllPatient() throws Exception {
		List<PatientRegistration> patientRegistrations= patientRegistrationRepository.findAllPatientWithNoDoctor();
		if(patientRegistrations.isEmpty())
			throw new Exception("All patient allocated to Doctor");
		List<PatientRegistrationDTO> pateintRegistartionDTOs = new ArrayList<>();
		for (PatientRegistration patientRegistration : patientRegistrations) {
			pateintRegistartionDTOs.add(PatientRegistrationDTO.patientRegistrationConvertPatientRegistrationDTO(patientRegistration));
		}
		return pateintRegistartionDTOs;
		
	}
	public String updatePatientRegistrationDetail(PatientRegistrationDetail patientRegistrationDetail,String patientId) throws Exception {
		PatientRegistration patientRegistration=patientRegistrationRepository.findById(patientId).orElseThrow(()-> new Exception("UserRegistrationService.NO_USER_FOUND"));
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDetail);
		patientRegistration= patientRegistrationRepository.save(patientRegistration);
		return patientRegistration.getPatientId();
	}
	public PatientRegistrationDTO getPatient(Login login)  throws Exception{
		System.out.print(login.getEmail());
		PatientRegistration patientRegistration= patientRegistrationRepository.findByEmail(login.getEmail(),login.getPassword()).orElseThrow(()-> new Exception("UserRegistrationService.NO_USER_FOUND_WITH_EMAIL"));
		return PatientRegistrationDTO.patientRegistrationConvertPatientRegistrationDTO(patientRegistration);
	}
	public String updatePatientWithPhysicalDetail(PatientRegistrationDTO patientRegistrationDTO) throws Exception {
		PatientRegistration patientRegistration=patientRegistrationRepository.findById(patientRegistrationDTO.getPatientId()).orElseThrow(()-> new Exception("UserRegistrationService.NO_USER_FOUND"));
		patientRegistration.setPatientPhysicalAndMedicalDetails(patientRegistrationDTO.getPatientPhysicalAndMedicalDetails());
		if(patientRegistrationDTO.getDoctorDetailDTO()!=null) {
			doctorDetailServiceImpl.getDoctorById(patientRegistrationDTO.getDoctorDetailDTO().getDoctorId());
			patientRegistration.setDoctorDetail(DoctorDetailDTO.doctorDetailDTOConvertDoctorDetail(patientRegistrationDTO.getDoctorDetailDTO()));
		}
		patientRegistration= patientRegistrationRepository.save(patientRegistration);
		return patientRegistration.getPatientId();
	}
	

} 
