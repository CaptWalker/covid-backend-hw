package com.infy.user;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.user.controller.PatientRegistrationController;
import com.infy.user.entities.DoctorDetail;
import com.infy.user.model.Address;
import com.infy.user.model.DoctorDetailDTO;
import com.infy.user.model.Login;
import com.infy.user.model.PatientPhysicalAndMedicalDetails;
import com.infy.user.model.PatientRegistrationDTO;
import com.infy.user.model.PatientRegistrationDetail;
import com.infy.user.model.Result;
import com.infy.user.model.TravelDetail;
import com.infy.user.service.DoctorDetailServiceImpl;
import com.infy.user.service.PatientRegistrationService;


public class PatientRegistrationControllerTest {
	private MockMvc mockMvc;
	@Mock
	private PatientRegistrationService patientRegistrationService;
	@Mock
	private DoctorDetailServiceImpl doctorDetailServiceImpl;
	@InjectMocks
	private PatientRegistrationController patientRegistrationController;
	@Mock
	private Environment environment;
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(patientRegistrationController).build();
	}
	protected String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper= new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	protected <T> T mapFromjson(String json,Class<T> clazz) throws JsonMappingException, JsonProcessingException{
		ObjectMapper objectMapper= new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	@Test
	public void addPatientTest() throws Exception{
		PatientRegistrationDTO patientRegistrationDTO = new PatientRegistrationDTO();
		PatientRegistrationDetail patientRegistrationDetail = new PatientRegistrationDetail();
		patientRegistrationDetail.setAadhar("111122223333");
		patientRegistrationDetail.setEmail("john.snow@gmail.com");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date registrationDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfRegistration(registrationDate);
		patientRegistrationDetail.setFirstName("John");
		patientRegistrationDetail.setLastName("Snow");
		patientRegistrationDetail.setMobileNo(9876543210l);
		patientRegistrationDetail.setPassword("JohnSnow");
		Address address = new  Address();
		address.setAddress1("162,Near Medipoint Hospital");
		address.setAddress2("hinjewadi ,pune");
		address.setCity("pune");
		address.setPincode(472442);
		address.setState("Maharastra");
		patientRegistrationDetail.setAddress(address);
		Date birthDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfBirth(birthDate);
		patientRegistrationDTO.setPatientRegistrationDetail(patientRegistrationDetail);
		String request=mapToJson(patientRegistrationDTO);
		Mockito.when(patientRegistrationService.addPatient(patientRegistrationDTO)).thenReturn("P1");
		Mockito.when(environment.getProperty("UserRegistrationController.ADD_USER")).thenReturn("Registration is done with id:");
		String url ="/patientregistration/addpatient";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(request)).andDo(print()).andReturn();
		long status =(long)mvcResult.getResponse().getStatus();
		Assert.assertEquals(201l, status);
		String content= mvcResult.getResponse().getContentAsString();
		Result result= mapFromjson(content, Result.class);
		Assert.assertEquals("Registration is done with id:P1" , result.getMessage());

	}
	@Test
	public void getPatientById() throws Exception {
		PatientRegistrationDTO patientRegistrationDTO = new PatientRegistrationDTO();
		PatientRegistrationDetail patientRegistrationDetail = new PatientRegistrationDetail();
		patientRegistrationDetail.setAadhar("111122223333");
		patientRegistrationDetail.setEmail("john.snow@gmail.com");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date registrationDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfRegistration(registrationDate);
		patientRegistrationDetail.setFirstName("John");
		patientRegistrationDetail.setLastName("Snow");
		patientRegistrationDetail.setMobileNo(9876543210l);
		patientRegistrationDetail.setPassword("JohnSnow");
		Address address = new  Address();
		address.setAddress1("162,Near Medipoint Hospital");
		address.setAddress2("hinjewadi ,pune");
		address.setCity("pune");
		address.setPincode(472442);
		address.setState("Maharastra");
		patientRegistrationDetail.setAddress(address);
		Date birthDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfBirth(birthDate);
		patientRegistrationDTO.setPatientId("P1");
		patientRegistrationDTO.setPatientRegistrationDetail(patientRegistrationDetail);
		Mockito.when(patientRegistrationService.getPatientById("P1")).thenReturn(patientRegistrationDTO);
		String url ="/patientregistration/getpatient/P1";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
		long status =(long)mvcResult.getResponse().getStatus();
		Assert.assertEquals(200l, status);
		String content= mvcResult.getResponse().getContentAsString();
		PatientRegistrationDTO patientRegistrationDTO2= mapFromjson(content, PatientRegistrationDTO.class);
		Assert.assertEquals(patientRegistrationDTO.getPatientId(), patientRegistrationDTO2.getPatientId());
	}
	@Test
	public void updatePatientRegisterationDetail() throws Exception {
		PatientRegistrationDetail patientRegistrationDetail = new PatientRegistrationDetail();
		patientRegistrationDetail.setAadhar("111122223333");
		patientRegistrationDetail.setEmail("john.snow@gmail.com");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date registrationDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfRegistration(registrationDate);
		patientRegistrationDetail.setFirstName("John");
		patientRegistrationDetail.setLastName("Snow");
		patientRegistrationDetail.setMobileNo(9876543210l);
		patientRegistrationDetail.setPassword("JohnSnow");
		Address address = new  Address();
		address.setAddress1("162,Near Medipoint Hospital");
		address.setAddress2("hinjewadi ,pune");
		address.setCity("pune");
		address.setPincode(472442);
		address.setState("Maharastra");
		patientRegistrationDetail.setAddress(address);
		Date birthDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfBirth(birthDate);
		String request=mapToJson(patientRegistrationDetail);
		Mockito.when(patientRegistrationService.updatePatientRegistrationDetail(patientRegistrationDetail, "P1")).thenReturn("P1");
		Mockito.when(environment.getProperty("UserRegistrationController.UPDATE_USER")).thenReturn("Details are updated with id:");
		String url ="/patientregistration/updatepatient/P1";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(request)).andDo(print()).andReturn();
		long status =(long)mvcResult.getResponse().getStatus();
		Assert.assertEquals(200l, status);
		String content= mvcResult.getResponse().getContentAsString();
		Result result= mapFromjson(content, Result.class);
		Assert.assertNotNull(result);
	}
	@Test
	public void getPatientByDoctorID () throws Exception{
		List<PatientRegistrationDTO> patientRegistrationDTOs= new ArrayList<PatientRegistrationDTO>();
		PatientRegistrationDTO patientRegistrationDTO =new PatientRegistrationDTO();
		patientRegistrationDTO.setPatientId("P1");
		patientRegistrationDTO.setDoctorDetailDTO(new DoctorDetailDTO());
		patientRegistrationDTO.getDoctorDetailDTO().setDoctorId("D1");
		patientRegistrationDTOs.add(patientRegistrationDTO);
		Mockito.when(patientRegistrationService.getPatientByDoctorId("D1")).thenReturn(patientRegistrationDTOs);
		String url ="/patientregistration/getpatients/D1";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
		long status =(long)mvcResult.getResponse().getStatus();
		Assert.assertEquals(200l, status);
		String content= mvcResult.getResponse().getContentAsString();
		PatientRegistrationDTO[] patientRegistrationDTOs1= mapFromjson(content, PatientRegistrationDTO[].class);
		Assert.assertEquals(patientRegistrationDTO.getPatientId(), patientRegistrationDTOs1[0].getPatientId());
	}
	@Test
	public void getAllPatient() throws Exception{
		List<PatientRegistrationDTO> patientRegistrationDTOs= new ArrayList<PatientRegistrationDTO>();
		PatientRegistrationDTO patientRegistrationDTO =new PatientRegistrationDTO();
		patientRegistrationDTO.setPatientId("P1");
		patientRegistrationDTOs.add(patientRegistrationDTO);
		Mockito.when(patientRegistrationService.getAllPatient()).thenReturn(patientRegistrationDTOs);
		String url ="/patientregistration/getallpatients";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
		long status =(long)mvcResult.getResponse().getStatus();
		Assert.assertEquals(200l, status);
		String content= mvcResult.getResponse().getContentAsString();
		PatientRegistrationDTO[] patientRegistrationDTOs1= mapFromjson(content, PatientRegistrationDTO[].class);
		Assert.assertEquals(patientRegistrationDTO.getPatientId(), patientRegistrationDTOs1[0].getPatientId());
	}
	@Test
	public void getPatientByUserNameAndMobileNumberAndEmail() throws Exception {
		PatientRegistrationDTO patientRegistrationDTO = new PatientRegistrationDTO();
		PatientRegistrationDetail patientRegistrationDetail = new PatientRegistrationDetail();
		patientRegistrationDetail.setAadhar("111122223333");
		patientRegistrationDetail.setEmail("john.snow@gmail.com");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date registrationDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfRegistration(registrationDate);
		patientRegistrationDetail.setFirstName("John");
		patientRegistrationDetail.setLastName("Snow");
		patientRegistrationDetail.setMobileNo(9876543210l);
		patientRegistrationDetail.setPassword("JohnSnow");
		Address address = new  Address();
		address.setAddress1("162,Near Medipoint Hospital");
		address.setAddress2("hinjewadi ,pune");
		address.setCity("pune");
		address.setPincode(472442);
		address.setState("Maharastra");
		patientRegistrationDetail.setAddress(address);
		Date birthDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfBirth(birthDate);
		patientRegistrationDTO.setPatientId("P1");
		patientRegistrationDTO.setPatientRegistrationDetail(patientRegistrationDetail);
		Mockito.when(patientRegistrationService.getPatientByNameAndmobileNumberAndEmailAddress("John", "john.snow@gmail.com",9876543210l)).thenReturn(patientRegistrationDTO);
		String url ="/patientregistration//getpatient/John/john.snow@gmail.com/9876543210";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
		long status =(long)mvcResult.getResponse().getStatus();
		Assert.assertEquals(200l, status);
		String content= mvcResult.getResponse().getContentAsString();
		PatientRegistrationDTO patientRegistrationDTO2= mapFromjson(content, PatientRegistrationDTO.class);
		Assert.assertEquals(patientRegistrationDTO.getPatientId(), patientRegistrationDTO2.getPatientId());
	}
	@Test
	public void getPatient() throws Exception {
		PatientRegistrationDTO patientRegistrationDTO = new PatientRegistrationDTO();
		PatientRegistrationDetail patientRegistrationDetail = new PatientRegistrationDetail();
		patientRegistrationDetail.setAadhar("111122223333");
		patientRegistrationDetail.setEmail("john.snow@gmail.com");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date registrationDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfRegistration(registrationDate);
		patientRegistrationDetail.setFirstName("John");
		patientRegistrationDetail.setLastName("Snow");
		patientRegistrationDetail.setMobileNo(9876543210l);
		patientRegistrationDetail.setPassword("JohnSnow");
		Address address = new  Address();
		address.setAddress1("162,Near Medipoint Hospital");
		address.setAddress2("hinjewadi ,pune");
		address.setCity("pune");
		address.setPincode(472442);
		address.setState("Maharastra");
		patientRegistrationDetail.setAddress(address);
		Date birthDate=sdf.parse("12-07-2020");
		patientRegistrationDetail.setDateOfBirth(birthDate);
		patientRegistrationDTO.setPatientId("P1");
		patientRegistrationDTO.setPatientRegistrationDetail(patientRegistrationDetail);
		Login login= new Login();
		login.setDocStatus(false);
		login.setEmail("john.snow@gmail.com");
		login.setPassword("Johnsnow");
		String request=mapToJson(login);
		Mockito.when(patientRegistrationService.getPatient(login)).thenReturn(patientRegistrationDTO);
		String url ="/patientregistration/login";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(request)).andDo(print()).andReturn();
		long status =(long)mvcResult.getResponse().getStatus();
		Assert.assertEquals(200l, status);
		String content= mvcResult.getResponse().getContentAsString();
		PatientRegistrationDTO patientRegistrationDTO2= mapFromjson(content, PatientRegistrationDTO.class);
		Assert.assertEquals(patientRegistrationDTO.getPatientId(), patientRegistrationDTO2.getPatientId());
	}
	@Test
	public void getpatientDoctorLogin()throws Exception{
		Login login= new Login();
		login.setDocStatus(true);
		login.setEmail("rahul.kumar@gmail.com");
		login.setPassword("rahul@123");
		String request=mapToJson(login);
		DoctorDetailDTO doctorDetailDTO = new DoctorDetailDTO();
		doctorDetailDTO.setCity("Pune");
		doctorDetailDTO.setDoctorId("D1");
		doctorDetailDTO.setDoctorName("Rahul kumar");
		doctorDetailDTO.setEmail("rahul.kumar@gmail.com");
		doctorDetailDTO.setHospitalName("Medipoint");
		doctorDetailDTO.setQualification("MBBS");
		doctorDetailDTO.setSpecialist("Cardiologist");
		doctorDetailDTO.setState("Maharastra");
		Mockito.when(doctorDetailServiceImpl.getDoctor(login)).thenReturn(doctorDetailDTO);
		String url ="/patientregistration/login";
		MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(request)).andDo(print()).andReturn();
		long status =(long)mvcResult.getResponse().getStatus();
		Assert.assertEquals(200l, status);
		String content= mvcResult.getResponse().getContentAsString();
		DoctorDetailDTO doctorDetailDTO1= mapFromjson(content, DoctorDetailDTO.class);
		Assert.assertEquals(doctorDetailDTO.getDoctorId(), doctorDetailDTO1.getDoctorId());
	}
	@Test
	public void updatePatientPhysicalAndMedicalHistory( ) throws Exception{
	PatientRegistrationDTO patientRegistrationDTO = new PatientRegistrationDTO();
	patientRegistrationDTO.setPatientId("P1");
	PatientPhysicalAndMedicalDetails details= new PatientPhysicalAndMedicalDetails();
	details.setWeightKg(70.0);
	details.setHeightCm(123.0);
	details.setGender('M');
	details.setTravelIn15Days(true);
	details.setAge(23);
	List<TravelDetail> travelDetails = new ArrayList<TravelDetail>();
	TravelDetail travelDetail = new TravelDetail();
	travelDetail.setFromPlace("Pune");
	travelDetail.setToPlace("Dehradhun");
	travelDetail.setStayDurationInDay(2);
	travelDetails.add(travelDetail);
	details.setTravelDetail(travelDetails);
	details.setQuarantineIn15Days(true);
	details.setBodyMassIndex(21);
	details.setSymptoms("Yes");
	details.setCough('Y');
	details.setCold('Y');
	details.setThroatPain('Y');
	details.setBloodPressure('Y');
	details.setDiabetes('Y');
	details.setLungDisease('Y');
	details.setHeartPatient('Y');
	details.setBoneFracture('Y');
	details.setAccidentalHistory('Y');
	details.setWorkType("Labour");
	details.setJunkFoodConsumptionFreq(2);
	details.setExerciseDurationPerDay(3);
	details.setMedicalInsurance(true);
	patientRegistrationDTO.setPatientPhysicalAndMedicalDetails(details);
	DoctorDetailDTO doctorDetail = new DoctorDetailDTO();
	doctorDetail.setCity("Pune");
	doctorDetail.setDoctorId("D1");
	doctorDetail.setDoctorName("Rahul kumar");
	doctorDetail.setEmail("rahul.kumar@gmail.com");
	doctorDetail.setHospitalName("Medipoint");
	doctorDetail.setQualification("MBBS");
	doctorDetail.setSpecialist("Cardiologist");
	doctorDetail.setState("Maharastra");
	patientRegistrationDTO.setDoctorDetailDTO(doctorDetail);
	String request=mapToJson(patientRegistrationDTO);
	Mockito.when(patientRegistrationService.updatePatientWithPhysicalDetail(patientRegistrationDTO)).thenReturn("P1");
	Mockito.when(environment.getProperty("UserRegistrationController.UPDATE_USER")).thenReturn("Details are updated with id: ");
	String url ="/patientregistration/updatepatientphysicaldetail";
	MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(request)).andDo(print()).andReturn();
	long status =(long)mvcResult.getResponse().getStatus();
	Assert.assertEquals(200l, status);
	String content= mvcResult.getResponse().getContentAsString();
	Result result= mapFromjson(content, Result.class);
	Assert.assertEquals("Details are updated with id: P1" , result.getMessage());
}
}
