package com.infy.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.user.model.DoctorDetailDTO;
import com.infy.user.model.Login;
import com.infy.user.model.PatientRegistrationDTO;
import com.infy.user.model.PatientRegistrationDetail;
import com.infy.user.model.Result;
import com.infy.user.service.DoctorDetailServiceImpl;
import com.infy.user.service.PatientRegistrationService;
@RestController
@RequestMapping("/patientregistration")
@CrossOrigin
public class PatientRegistrationController {
	@Autowired
	PatientRegistrationService patientRegistrationService;
	@Autowired
	Environment  environment;
	@Autowired
	DoctorDetailServiceImpl doctorDetailServiceImpl;
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "User Registration Service is up and running";
	}
	@PostMapping("/addpatient")
	public ResponseEntity<Result> addUser(@RequestBody PatientRegistrationDTO patientRegistrationDTO)throws Exception{
		String id = patientRegistrationService.addPatient(patientRegistrationDTO);
		String message="User is registered with"+id;
		Result result = new Result(message);
		ResponseEntity<Result> responseEntity= new ResponseEntity<>(result,HttpStatus.CREATED);
		return responseEntity;
	}
	@PostMapping("/updatepatient/{patientId}")
	public ResponseEntity<Result> updatePatientRegistrationDetail(@RequestBody PatientRegistrationDetail patientRegistrationDetail,@PathVariable String patientId) throws Exception{
		String id = patientRegistrationService.updatePatientRegistrationDetail(patientRegistrationDetail, patientId);
		String message="User is updated with"+id;
		Result result = new Result(message);
		ResponseEntity<Result> responseEntity= new ResponseEntity<>(result,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/getpatients/{doctorId}")
	public ResponseEntity<List<PatientRegistrationDTO>> getPatientByDoctorID(@PathVariable String doctorId) throws Exception{
		List<PatientRegistrationDTO> patientRegistrationDTOs = patientRegistrationService.getPatientByDoctorId(doctorId);
		ResponseEntity<List<PatientRegistrationDTO>> responseEntity= new ResponseEntity<>(patientRegistrationDTOs,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/getallpatients")
	public ResponseEntity<List<PatientRegistrationDTO>> getAllPatient() throws Exception{
		List<PatientRegistrationDTO> patientRegistrationDTOs = patientRegistrationService.getAllPatient();
		ResponseEntity<List<PatientRegistrationDTO>> responseEntity= new ResponseEntity<>(patientRegistrationDTOs,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/getpatient/{id}")
	public ResponseEntity<PatientRegistrationDTO> getPatientById(@PathVariable String id) throws Exception{
		PatientRegistrationDTO patientRegistrationDTO = patientRegistrationService.getPatientById(id);
		ResponseEntity<PatientRegistrationDTO> responseEntity= new ResponseEntity<>(patientRegistrationDTO,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/getpatient/{firstName}/{emailAddress}/{mobileNumber}")
	public ResponseEntity<PatientRegistrationDTO> getPatientByNameAndmobileNumberAndEmailAddress(@PathVariable String firstName,@PathVariable String emailAddress,@PathVariable Long mobileNumber) throws Exception{
		PatientRegistrationDTO patientRegistrationDTO = patientRegistrationService.getPatientByNameAndmobileNumberAndEmailAddress(firstName, emailAddress, mobileNumber);
		ResponseEntity<PatientRegistrationDTO> responseEntity= new ResponseEntity<>(patientRegistrationDTO,HttpStatus.OK);
		return responseEntity;
	}
	@PostMapping("/login")
	public ResponseEntity<Object> getPatientByEmail(@RequestBody Login login) throws Exception{
		ResponseEntity<Object> responseEntity;
		if(login.isDocStatus()) {
			DoctorDetailDTO doctorDetailDTO= doctorDetailServiceImpl.getDoctor(login);
			 responseEntity= new ResponseEntity<>(doctorDetailDTO,HttpStatus.OK);
				
		}else {
		PatientRegistrationDTO patientRegistrationDTO = patientRegistrationService.getPatient(login);
		responseEntity= new ResponseEntity<>(patientRegistrationDTO,HttpStatus.OK);
		}
		return responseEntity;
	}
	@PostMapping("/updatepatientphysicaldetail")
	public ResponseEntity<Result> updatePatientWithPhysicalDetail(@RequestBody PatientRegistrationDTO patientRegistrationDTO) throws Exception{
		String id = patientRegistrationService.updatePatientWithPhysicalDetail(patientRegistrationDTO);
		String message="User is updated with"+id;
		Result result = new Result(message);
		ResponseEntity<Result> responseEntity= new ResponseEntity<>(result,HttpStatus.OK);
		return responseEntity;
	}
}
