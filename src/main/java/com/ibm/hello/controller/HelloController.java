package com.ibm.hello.controller;

import static com.ibm.hello.service.ServiceNameConstants.HELLO_NAME;
import static com.ibm.hello.service.ServiceNameConstants.HOLA_NAME;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.hello.config.ServiceConfig;
import com.ibm.hello.model.GreetingRequest;
import com.ibm.hello.model.GreetingResponse;
import com.ibm.hello.service.GreetingService;
import com.ibm.hello.service.ServiceName;
import com.ibm.hello.model.Address;
import com.ibm.hello.model.CovidData;
import com.ibm.hello.model.DoctorDetailDTO;
import com.ibm.hello.model.PatientPhysicalAndMedicalDetails;
import com.ibm.hello.model.PatientRegistrationDTO;
import com.ibm.hello.model.PatientRegistrationDetail;
import com.ibm.hello.model.TravelDetail;

@RestController
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    private final BeanFactory beanFactory;
    private final ServiceConfig serviceConfig;

    public HelloController(BeanFactory beanFactory, ServiceConfig serviceConfig) {
        this.beanFactory = beanFactory;
        this.serviceConfig = serviceConfig;
    }

    @GetMapping(value = "/getpatient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PatientRegistrationDTO> helloWorld(    ) {
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
		PatientRegistrationDTO patientRegistrationDTO = new PatientRegistrationDTO();
		patientRegistrationDTO.setPatientId("P1");
		PatientRegistrationDetail patientRegistrationDetail = new PatientRegistrationDetail();
		patientRegistrationDetail.setAadhar("111122223333");
		patientRegistrationDetail.setEmail("john.snow@gmail.com");
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
		patientRegistrationDTO.setPatientRegistrationDetail(patientRegistrationDetail);
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
		patientRegistrationDTO.setPatientRegistrationDetail(patientRegistrationDetail);
		patientRegistrationDTO.setCovidDataList(covidDatas);
        return ResponseEntity.ok(patientRegistrationDTO
             );
    }

    @PostMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Content body is missing"),
            @ApiResponse(code = 406, message = "Name parameter missing"),
            @ApiResponse(code = 415, message = "Missing content type")
    })
    public ResponseEntity<GreetingResponse> helloWorld(
            @RequestBody GreetingRequest request,
            @ApiParam(
                    allowableValues = HELLO_NAME + "," + HOLA_NAME,
                    value = "the beanName for the service implementation that should be used to fulfill the request")
            @RequestHeader(name = "serviceName", required = false) final String serviceName
    ) {

        LOGGER.debug("Processing name: " + request.getName());

        if (StringUtils.isEmpty(request.getName())) {
            return ResponseEntity.status(406).build();
        }

        return ResponseEntity.ok(getGreetingService(serviceName).getGreeting(request.getName()));
    }

    protected GreetingService getGreetingService(String serviceNameHeader) {
        final ServiceName serviceName = ServiceName.safeValueOf(
                serviceNameHeader,
                serviceConfig.getBeanName());

        if (serviceName == null) {
            throw new ApplicationConfigurationError("ServiceConfig.beanName from ");
        }

        return beanFactory.getBean(serviceName.simpleName(), GreetingService.class);
    }

    public static class ApplicationConfigurationError extends RuntimeException {
        public ApplicationConfigurationError(String message) {
            super(message);
        }
    }
}
