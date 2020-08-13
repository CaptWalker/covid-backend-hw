package com.infy.user.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.infy.user.model.DoctorDetailDTO;
import com.infy.user.service.DoctorDetailServiceImpl;
@RestController
@CrossOrigin
@RequestMapping("/doctor/")
public class DoctorDetailController {
	@Autowired
	private DoctorDetailServiceImpl doctorDetailServiceImpl;
	@Autowired
	private Environment environment;
	@PostMapping("/addDoctor")
	public ResponseEntity<String> addDoctor(@RequestBody DoctorDetailDTO doctorDetailDTO){
	String doctorId= doctorDetailServiceImpl.addDoctor(doctorDetailDTO);
	String message=environment.getProperty("DoctorDetailController.ADD_DOCTOR")+doctorId;
	ResponseEntity<String>  responseEntity = new ResponseEntity<String>(message,HttpStatus.CREATED);
	return responseEntity;
	}
	@GetMapping("/getDoctorById/{doctorId}")
	public ResponseEntity<DoctorDetailDTO> getDoctorById(@PathVariable String doctorId) throws Exception{
		DoctorDetailDTO doctorDetailDTO= doctorDetailServiceImpl.getDoctorById(doctorId);
		ResponseEntity<DoctorDetailDTO>  responseEntity = new ResponseEntity<DoctorDetailDTO>(doctorDetailDTO,HttpStatus.CREATED);
		return responseEntity;
		}
	
}
