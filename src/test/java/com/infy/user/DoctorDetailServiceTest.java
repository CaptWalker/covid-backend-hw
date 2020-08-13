package com.infy.user;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.user.entities.DoctorDetail;
import com.infy.user.model.DoctorDetailDTO;
import com.infy.user.model.Login;
import com.infy.user.repository.DoctorDetailRepository;
import com.infy.user.service.DoctorDetailServiceImpl;
import com.infy.user.service.SequenceGeneratorService;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDetailServiceTest {
	@InjectMocks
	private DoctorDetailServiceImpl doctorDetailServiceImpl;
	@Mock
	private SequenceGeneratorService sequenceGeneratorService;
	@Mock
	private DoctorDetailRepository doctorDetailRepository;
	@Rule
	public ExpectedException expectedException= ExpectedException.none();
	@Test
	public void addDoctor() {
		DoctorDetailDTO doctorDetailDTO = new DoctorDetailDTO();
		doctorDetailDTO.setCity("Pune");
		doctorDetailDTO.setDoctorName("Rahul kumar");
		doctorDetailDTO.setEmail("rahul.kumar@gmail.com");
		doctorDetailDTO.setHospitalName("Medipoint");
		doctorDetailDTO.setQualification("MBBS");
		doctorDetailDTO.setSpecialist("Cardiologist");
		doctorDetailDTO.setState("Maharastra");
		Mockito.when(sequenceGeneratorService.generateSequence(DoctorDetail.getSequence())).thenReturn(1l);
		DoctorDetail doctorDetail= DoctorDetailDTO.doctorDetailDTOConvertDoctorDetail(doctorDetailDTO);
		doctorDetail.setDoctorId("D1");
		Mockito.when(doctorDetailRepository.save(doctorDetail)).thenReturn(doctorDetail);
		Assert.assertNotNull(doctorDetailServiceImpl.addDoctor(doctorDetailDTO));
	}
	@Test
	public void getDoctorById()  throws Exception{
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setDoctorId("D1");
		doctorDetail.setCity("Pune");
		doctorDetail.setDoctorName("Rahul kumar");
		doctorDetail.setEmail("rahul.kumar@gmail.com");
		doctorDetail.setHospitalName("Medipoint");
		doctorDetail.setQualification("MBBS");
		doctorDetail.setSpecialist("Cardiologist");
		doctorDetail.setState("Maharastra");
		Optional<DoctorDetail> optional =Optional.of(doctorDetail);
		Mockito.when(doctorDetailRepository.findById("D1")).thenReturn(optional);
		Assert.assertNotNull(doctorDetailServiceImpl.getDoctorById("D1"));
	}
	@Test
	public void getDoctorByIdInvalidId()  throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("doctor is not Found with id");
		Mockito.when(doctorDetailRepository.findById("D1")).thenReturn(Optional.empty());
		Assert.assertNotNull(doctorDetailServiceImpl.getDoctorById("D1"));
	}
	@Test
	public void getDoctor()  throws Exception{
		Login login = new Login();
		login.setEmail("rahul.kumar@gmail.com");
		login.setPassword("rahul123");
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setDoctorId("D1");
		doctorDetail.setCity("Pune");
		doctorDetail.setPassword("rahul123");
		doctorDetail.setDoctorName("Rahul kumar");
		doctorDetail.setEmail("rahul.kumar@gmail.com");
		doctorDetail.setHospitalName("Medipoint");
		doctorDetail.setQualification("MBBS");
		doctorDetail.setSpecialist("Cardiologist");
		doctorDetail.setState("Maharastra");
		Optional<DoctorDetail> optional =Optional.of(doctorDetail);
		Mockito.when(doctorDetailRepository.findByEmailAndPassword(login.getEmail(),login.getPassword())).thenReturn(optional);
		Assert.assertNotNull(doctorDetailServiceImpl.getDoctor(login));
	}
	@Test
	public void getDoctorInvalidCredentail()  throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("doctor is not Found");
		Login login = new Login();
		login.setEmail("rahul.kumar@gmail.com");
		login.setPassword("rahul123");
		Mockito.when(doctorDetailRepository.findByEmailAndPassword(login.getEmail(),login.getPassword())).thenReturn(Optional.empty());
		Assert.assertNotNull(doctorDetailServiceImpl.getDoctor(login));
	}
}
