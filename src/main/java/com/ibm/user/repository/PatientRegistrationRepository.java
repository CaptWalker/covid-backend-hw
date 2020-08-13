package com.infy.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.infy.user.entities.PatientRegistration;

public interface PatientRegistrationRepository extends MongoRepository<PatientRegistration, String>{
	@Query("{'patientRegistrationDetail.firstName':?0,'patientRegistrationDetail.mobileNo':?1,'patientRegistrationDetail.email':?2}")
	Optional<PatientRegistration> findByUserNameandMobileNumberandEmailAddress(String userName, Long mobileNumber,
			String emailAddress);
	@Query("{'patientRegistrationDetail.email':?0,'patientRegistrationDetail.password':?1}")
	Optional<PatientRegistration> findByEmail(String email, String password);
	@Query("{'doctorDetail.doctorId':?0}")
	List<PatientRegistration> findByDoctorId(String doctorId);
	@Query("{'doctorDetail':{$exists:false}}")
	List<PatientRegistration> findAllPatientWithNoDoctor();
}
