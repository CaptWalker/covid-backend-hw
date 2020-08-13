package com.infy.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.infy.user.entities.PatientRegistration;
import com.infy.user.model.Address;
import com.infy.user.model.CovidData;
import com.infy.user.model.DoctorDetailDTO;
import com.infy.user.model.Login;
import com.infy.user.model.PatientPhysicalAndMedicalDetails;
import com.infy.user.model.PatientRegistrationDTO;
import com.infy.user.model.PatientRegistrationDetail;
import com.infy.user.model.TravelDetail;
import com.infy.user.repository.PatientRegistrationRepository;
import com.infy.user.service.DoctorDetailServiceImpl;
import com.infy.user.service.PatientRegistrationServiceImpl;
import com.infy.user.service.SequenceGeneratorService;



@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientRegistrationServiceTest {
	@Mock
	private DoctorDetailServiceImpl doctorDetailServiceImpl;
	@Mock
	private SequenceGeneratorService sequenceGeneratorService;
	@Mock
	private PatientRegistrationRepository patientRegistrationRepository;
	@InjectMocks
	private PatientRegistrationServiceImpl patientRegistrationServiceImpl;
	@Rule
	public ExpectedException expectedException= ExpectedException.none();
	@Test
	public void findByIdValidId() throws Exception{
		CovidData covidData = new CovidData();
		covidData.setBcgVaccine('Y');
		covidData.setCollectionDate(new Date());
		covidData.setCovidTestResult("Negative");
		covidData.setFluVaccine('Y');
		covidData.setImmunoCompromisedCondition("No");
		covidData.setPatientCategory("cat1");
		List<String> specimenType = new ArrayList<>();
		specimenType.add("Nasal Swab");
		covidData.setSpecimenType(specimenType);
		covidData.setSampleId(1l);
		covidData.setPatientId("P1");
		ArrayList<CovidData> covidDatas = new ArrayList<CovidData>();
		covidDatas.add(covidData);
		PatientRegistration patientRegistration = new PatientRegistration();
		patientRegistration.setPatientId("P1");
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
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDetail);
		PatientPhysicalAndMedicalDetails details= new PatientPhysicalAndMedicalDetails();
		details.setWeightKg(70.0);
		details.setHeightCm(123.0);
		details.setGender('M');
		details.setTravelIn15Days(true);
		details.setAge(23);
		List<TravelDetail> travelDetails = new ArrayList<TravelDetail>();
		TravelDetail travelDetail = new TravelDetail();
		Date dateOfTravel=sdf.parse("12-07-2020");
		travelDetail.setDateOfTravel(dateOfTravel);
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
		patientRegistration.setPatientPhysicalAndMedicalDetails(details);
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setCity("Pune");
		doctorDetail.setDoctorId("D1");
		doctorDetail.setDoctorName("Rahul kumar");
		doctorDetail.setEmail("rahul.kumar@gmail.com");
		doctorDetail.setHospitalName("Medipoint");
		doctorDetail.setQualification("MBBS");
		doctorDetail.setSpecialist("Cardiologist");
		doctorDetail.setState("Maharastra");
		patientRegistration.setDoctorDetail(doctorDetail);
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDetail);
		patientRegistration.setCovidDataList(covidDatas);
		Optional<PatientRegistration> optional= Optional.of(patientRegistration);
		Mockito.when(patientRegistrationRepository.findById("P1")).thenReturn(optional);
		PatientRegistrationDTO patientRegisterationDTO= patientRegistrationServiceImpl.getPatientById("P1");
		Assert.assertNotNull(patientRegisterationDTO);
		Assert.assertEquals(patientRegistration.getPatientId(), patientRegisterationDTO.getPatientId());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getAadhar(), patientRegisterationDTO.getPatientRegistrationDetail().getAadhar());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getEmail(), patientRegisterationDTO.getPatientRegistrationDetail().getEmail());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getFirstName(), patientRegisterationDTO.getPatientRegistrationDetail().getFirstName());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getLastName(), patientRegisterationDTO.getPatientRegistrationDetail().getLastName());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getPassword(), patientRegisterationDTO.getPatientRegistrationDetail().getPassword());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getAddress().getAddress1(), patientRegisterationDTO.getPatientRegistrationDetail().getAddress().getAddress1());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getAddress().getAddress2(), patientRegisterationDTO.getPatientRegistrationDetail().getAddress().getAddress2());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getAddress().getCity(), patientRegisterationDTO.getPatientRegistrationDetail().getAddress().getCity());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getAddress().getState(), patientRegisterationDTO.getPatientRegistrationDetail().getAddress().getState());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getAddress().getPincode(), patientRegisterationDTO.getPatientRegistrationDetail().getAddress().getPincode());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getDateOfBirth(), patientRegisterationDTO.getPatientRegistrationDetail().getDateOfBirth());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getDateOfRegistration(), patientRegisterationDTO.getPatientRegistrationDetail().getDateOfRegistration());
		Assert.assertEquals(patientRegistration.getPatientRegistrationDetail().getMobileNo(), patientRegisterationDTO.getPatientRegistrationDetail().getMobileNo());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getSymptoms(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getSymptoms());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getWorkType(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getWorkType());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getAccidentalHistory(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getAccidentalHistory());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getAge(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getAge());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getBloodPressure(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getBloodPressure());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getBodyMassIndex(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getBodyMassIndex());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getBoneFracture(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getBoneFracture());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getCold(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getCold());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getCough(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getCough());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getDiabetes(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getDiabetes());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getExerciseDurationPerDay(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getExerciseDurationPerDay());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getGender(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getGender());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getHeartPatient(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getHeartPatient());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getHeightCm(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getHeightCm());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getJunkFoodConsumptionFreq(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getJunkFoodConsumptionFreq());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getLungDisease(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getLungDisease());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getMedicalInsurance(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getMedicalInsurance());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getQuarantineIn15Days(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getQuarantineIn15Days());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getThroatPain(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getThroatPain());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getTravelIn15Days(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getTravelIn15Days());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getWeightKg(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getWeightKg());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getTravelDetail().get(0).getFromPlace(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getTravelDetail().get(0).getFromPlace());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getTravelDetail().get(0).getToPlace(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getTravelDetail().get(0).getToPlace());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getTravelDetail().get(0).getDateOfTravel(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getTravelDetail().get(0).getDateOfTravel());
		Assert.assertEquals(patientRegistration.getPatientPhysicalAndMedicalDetails().getTravelDetail().get(0).getStayDurationInDay(), patientRegisterationDTO.getPatientPhysicalAndMedicalDetails().getTravelDetail().get(0).getStayDurationInDay());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getDoctorId(),patientRegisterationDTO.getDoctorDetailDTO().getDoctorId());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getCity(),patientRegisterationDTO.getDoctorDetailDTO().getCity());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getDoctorName(),patientRegisterationDTO.getDoctorDetailDTO().getDoctorName());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getEmail(),patientRegisterationDTO.getDoctorDetailDTO().getEmail());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getHospitalName(),patientRegisterationDTO.getDoctorDetailDTO().getHospitalName());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getPassword(),patientRegisterationDTO.getDoctorDetailDTO().getPassword());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getQualification(),patientRegisterationDTO.getDoctorDetailDTO().getQualification());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getSpecialist(),patientRegisterationDTO.getDoctorDetailDTO().getSpecialist());
		Assert.assertEquals(patientRegistration.getDoctorDetail().getState(),patientRegisterationDTO.getDoctorDetailDTO().getState());
		Assert.assertEquals(patientRegistration.getCovidDataList().get(0).getCovidTestResult(),patientRegistration.getCovidDataList().get(0).getCovidTestResult());
		Assert.assertEquals(patientRegistration.getCovidDataList().get(0).getImmunoCompromisedCondition(),patientRegistration.getCovidDataList().get(0).getImmunoCompromisedCondition());
		Assert.assertEquals(patientRegistration.getCovidDataList().get(0).getPatientCategory(),patientRegistration.getCovidDataList().get(0).getPatientCategory());
		Assert.assertEquals(patientRegistration.getCovidDataList().get(0).getPatientId(),patientRegistration.getCovidDataList().get(0).getPatientId());
		Assert.assertEquals(patientRegistration.getCovidDataList().get(0).getBcgVaccine(),patientRegistration.getCovidDataList().get(0).getBcgVaccine());
		Assert.assertEquals(patientRegistration.getCovidDataList().get(0).getCollectionDate(),patientRegistration.getCovidDataList().get(0).getCollectionDate());
		Assert.assertEquals(patientRegistration.getCovidDataList().get(0).getFluVaccine(),patientRegistration.getCovidDataList().get(0).getFluVaccine());
		Assert.assertEquals(patientRegistration.getCovidDataList().get(0).getSampleId(),patientRegistration.getCovidDataList().get(0).getSampleId());
		
	
	}
	@Test
	public void findByIdInvalidId() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("UserRegistrationService.NO_USER_FOUND");
		Mockito.when(patientRegistrationRepository.findById("P2")).thenReturn(Optional.empty());
		patientRegistrationServiceImpl.getPatientById("P2");
	}
	@Test
	public void addPatient() throws Exception{
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
		DoctorDetailDTO doctorDetailDTO = new DoctorDetailDTO();
		doctorDetailDTO.setCity("Pune");
		doctorDetailDTO.setDoctorId("D1");
		doctorDetailDTO.setDoctorName("Rahul kumar");
		doctorDetailDTO.setEmail("rahul.kumar@gmail.com");
		doctorDetailDTO.setHospitalName("Medipoint");
		doctorDetailDTO.setQualification("MBBS");
		doctorDetailDTO.setSpecialist("Cardiologist");
		doctorDetailDTO.setState("Maharastra");
		patientRegistrationDTO.setDoctorDetailDTO(doctorDetailDTO);
		PatientRegistration patientRegistration= PatientRegistrationDTO.patientRegistrationDTOConvertPatientRegistration(patientRegistrationDTO);
		patientRegistration.setPatientId("P1");
		Mockito.when(sequenceGeneratorService.generateSequence(PatientRegistration.getSequence())).thenReturn(1l);
		Mockito.when(doctorDetailServiceImpl.getDoctorById("D1")).thenReturn(doctorDetailDTO);
		Mockito.when(patientRegistrationRepository.save(patientRegistration)).thenReturn(patientRegistration);
		String id = patientRegistrationServiceImpl.addPatient(patientRegistrationDTO);
		Assert.assertEquals( "P1", id);
	}
	@Test
	public void getPatientByNameAndMobileNo() throws Exception{
		PatientRegistration patientRegistration = new PatientRegistration();
		patientRegistration.setPatientId("P1");
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
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDetail);
		Optional< PatientRegistration> optional= Optional.of(patientRegistration);
		Mockito.when(patientRegistrationRepository.findByUserNameandMobileNumberandEmailAddress("John",9876543210l , "john.snow@gmail.com")).thenReturn(optional);
		PatientRegistrationDTO pa =patientRegistrationServiceImpl.getPatientByNameAndmobileNumberAndEmailAddress("John", "john.snow@gmail.com",9876543210l );
		Assert.assertNotNull(pa.getPatientId());
	}
	@Test
	public void getPatientByNameAndMobileNoInvalid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("UserRegistrationService.NO_USER_FOUND");
		Mockito.when(patientRegistrationRepository.findByUserNameandMobileNumberandEmailAddress("John",9876543210l , "john.snow@gmail.com")).thenReturn(Optional.empty());
		patientRegistrationServiceImpl.getPatientByNameAndmobileNumberAndEmailAddress("John", "john.snow@gmail.com",9876543210l );
		
	}
	@Test
	public void getPatientInvalid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("UserRegistrationService.NO_USER_FOUND");
		Login login = new Login();
		login.setEmail("john.snow@gmail.com");
		login.setPassword("johnsnow");
		Mockito.when(patientRegistrationRepository.findByEmail("john.snow@gmail.com","johnsnow")).thenReturn(Optional.empty());
		patientRegistrationServiceImpl.getPatient(login);
		
	}
	@Test
	public void getPatient() throws Exception{
		Login login = new Login();
		login.setEmail("john.snow@gmail.com");
		login.setPassword("johnsnow");
		PatientRegistration patientRegistration = new PatientRegistration();
		patientRegistration.setPatientId("P1");
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
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDetail);
		Optional< PatientRegistration> optional= Optional.of(patientRegistration);
		Mockito.when(patientRegistrationRepository.findByEmail("john.snow@gmail.com","johnsnow")).thenReturn(optional);
		PatientRegistrationDTO pa =patientRegistrationServiceImpl.getPatient(login);
		Assert.assertNotNull(pa.getPatientId());
	}
	@Test
	public void updatePatientRegistrationDetail() throws Exception{
		PatientRegistration patientRegistration = new PatientRegistration();
		patientRegistration.setPatientId("P1");
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
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDetail);
		Optional< PatientRegistration> optional= Optional.of(patientRegistration);
		Mockito.when(patientRegistrationRepository.findById("P1")).thenReturn(optional);
		Mockito.when(patientRegistrationRepository.save(patientRegistration)).thenReturn(patientRegistration);
		patientRegistrationDetail.setAadhar("222233334444");
		String id= patientRegistrationServiceImpl.updatePatientRegistrationDetail(patientRegistrationDetail, "P1");
		Assert.assertNotNull(id);
	}
	@Test
	public void getPatientByDoctorIdInvalid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("No patient allocated to Doctor");
		Mockito.when(patientRegistrationRepository.findByDoctorId("D1")).thenReturn(new ArrayList<PatientRegistration>());
		patientRegistrationServiceImpl.getPatientByDoctorId("D1");
		
	}
	
	@Test
	public void getPatientByDoctorId() throws Exception {
		PatientRegistration patientRegistration = new PatientRegistration();
		patientRegistration.setPatientId("P1");
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
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDetail);
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setCity("Pune");
		doctorDetail.setDoctorId("D1");
		doctorDetail.setDoctorName("Rahul kumar");
		doctorDetail.setEmail("rahul.kumar@gmail.com");
		doctorDetail.setHospitalName("Medipoint");
		doctorDetail.setQualification("MBBS");
		doctorDetail.setSpecialist("Cardiologist");
		doctorDetail.setState("Maharastra");
		patientRegistration.setDoctorDetail(doctorDetail);
		List<PatientRegistration> patientRegistrations= new ArrayList<PatientRegistration>();
		patientRegistrations.add(patientRegistration);
		Mockito.when(patientRegistrationRepository.findByDoctorId("D1")).thenReturn(patientRegistrations);
		List<PatientRegistrationDTO> pa=patientRegistrationServiceImpl.getPatientByDoctorId("D1");
		Assert.assertNotNull(pa.get(0).getPatientId());
	}
	@Test
	public void getAllPatient() throws Exception{
		PatientRegistration patientRegistration = new PatientRegistration();
		patientRegistration.setPatientId("P1");
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
		patientRegistration.setPatientRegistrationDetail(patientRegistrationDetail);
		List<PatientRegistration> patientRegistrations= new ArrayList<PatientRegistration>();
		patientRegistrations.add(patientRegistration);
		Mockito.when(patientRegistrationRepository.findAllPatientWithNoDoctor()).thenReturn(patientRegistrations);
		List<PatientRegistrationDTO> pa=patientRegistrationServiceImpl.getAllPatient();
		Assert.assertNotNull(pa.get(0).getPatientId());
	}
	@Test
	public void getAllPatientInvalid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("All patient allocated to Doctor");
		Mockito.when(patientRegistrationRepository.findAllPatientWithNoDoctor()).thenReturn(new ArrayList<PatientRegistration>());
		patientRegistrationServiceImpl.getAllPatient();
	}
	@Test
	public void updatePatientWithPhysicalDetail() throws Exception{
		PatientRegistrationDTO patientRegistrationDTO = new PatientRegistrationDTO();
		DoctorDetailDTO doctorDetailDTO = new DoctorDetailDTO();
		doctorDetailDTO.setCity("Pune");
		doctorDetailDTO.setDoctorId("D1");
		doctorDetailDTO.setDoctorName("Rahul kumar");
		doctorDetailDTO.setEmail("rahul.kumar@gmail.com");
		doctorDetailDTO.setHospitalName("Medipoint");
		doctorDetailDTO.setQualification("MBBS");
		doctorDetailDTO.setSpecialist("Cardiologist");
		doctorDetailDTO.setState("Maharastra");
		patientRegistrationDTO.setDoctorDetailDTO(doctorDetailDTO);
		patientRegistrationDTO.setPatientId("P1");
		PatientPhysicalAndMedicalDetails details= new PatientPhysicalAndMedicalDetails();
		details.setWeightKg(70.0);
		details.setHeightCm(123.0);
		details.setGender('M');
		details.setTravelIn15Days(true);
		details.setAge(23);
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
		PatientRegistration patientRegistration= PatientRegistrationDTO.patientRegistrationDTOConvertPatientRegistration(patientRegistrationDTO);
		Optional< PatientRegistration> optional= Optional.of(patientRegistration);
		Mockito.when(patientRegistrationRepository.findById("P1")).thenReturn(optional);
		Mockito.when(doctorDetailServiceImpl.getDoctorById("D1")).thenReturn(doctorDetailDTO);
		Mockito.when(patientRegistrationRepository.save(patientRegistration)).thenReturn(patientRegistration);
		String id = patientRegistrationServiceImpl.updatePatientWithPhysicalDetail(patientRegistrationDTO);
		Assert.assertEquals( "P1", id);
	}
}
