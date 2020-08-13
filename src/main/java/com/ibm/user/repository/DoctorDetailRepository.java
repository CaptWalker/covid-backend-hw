package com.infy.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.infy.user.entities.DoctorDetail;

public interface DoctorDetailRepository extends MongoRepository<DoctorDetail, String>{

	public List<DoctorDetail> findByCity(String city);

	public List<DoctorDetail> findBySpecialist(String specialist);
	@Query(value="{state:?0}",fields="{city:1,_id:0}")
	public List<DoctorDetail> findByStateDistinct(String state);

	public Optional<DoctorDetail> findByEmailAndPassword(String email, String password);

}
