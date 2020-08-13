package com.infy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.user.entities.DoctorDetail;
import com.infy.user.model.DoctorDetailDTO;
import com.infy.user.model.Login;
import com.infy.user.repository.DoctorDetailRepository;
@Service
public class DoctorDetailServiceImpl {
	@Autowired
	DoctorDetailRepository doctorDetailRepository;
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	public String addDoctor(DoctorDetailDTO doctorDetailDTO) {
		DoctorDetail doctorDetail = DoctorDetailDTO.doctorDetailDTOConvertDoctorDetail(doctorDetailDTO);
		doctorDetail.setDoctorId("D"+sequenceGeneratorService.generateSequence(DoctorDetail.getSequence()));
		doctorDetail= doctorDetailRepository.save(doctorDetail);
		 return doctorDetail.getDoctorId();
	}
	public DoctorDetailDTO getDoctorById(String doctorId) throws Exception {
		DoctorDetail doctorDetail = doctorDetailRepository.findById(doctorId).orElseThrow( ()->new Exception("doctor is not Found with id"));
		return DoctorDetailDTO.doctorDetailConvertDoctorDetailDTO(doctorDetail);
	}
	public DoctorDetailDTO getDoctor(Login login) throws Exception{
		DoctorDetail doctorDetail = doctorDetailRepository.findByEmailAndPassword(login.getEmail(),login.getPassword()).orElseThrow( ()->new Exception("doctor is not Found"));
		return DoctorDetailDTO.doctorDetailConvertDoctorDetailDTO(doctorDetail);
	}
	
}
