package com.infy.user.model;

import java.util.List;

public class PatientPhysicalAndMedicalDetails {
	private Double weightKg;
	private Double heightCm;
	private Character gender;
	private Integer age;
	private Boolean travelIn15Days;
	private List<TravelDetail> travelDetail;
	private Boolean quarantineIn15Days;
	private Integer bodyMassIndex;
	private String symptoms;
	private Character cough;
	private Character cold;
	private Character throatPain;
	private Character bloodPressure;
	private Character diabetes;
	private Character lungDisease;
	private Character heartPatient;
	private Character boneFracture;
	private Character accidentalHistory;
	private String workType;
	private Integer junkFoodConsumptionFreq;
	private Integer exerciseDurationPerDay;
	private Boolean medicalInsurance;
	public Double getWeightKg() {
		return weightKg;
	}
	public void setWeightKg(Double weightKg) {
		this.weightKg = weightKg;
	}
	public Double getHeightCm() {
		return heightCm;
	}
	public void setHeightCm(Double heightCm) {
		this.heightCm = heightCm;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getTravelIn15Days() {
		return travelIn15Days;
	}
	public void setTravelIn15Days(Boolean travelIn15Days) {
		this.travelIn15Days = travelIn15Days;
	}
	public List<TravelDetail> getTravelDetail() {
		return travelDetail;
	}
	public void setTravelDetail(List<TravelDetail> travelDetail) {
		this.travelDetail = travelDetail;
	}
	public Boolean getQuarantineIn15Days() {
		return quarantineIn15Days;
	}
	public void setQuarantineIn15Days(Boolean quarantineIn15Days) {
		this.quarantineIn15Days = quarantineIn15Days;
	}
	public Integer getBodyMassIndex() {
		return bodyMassIndex;
	}
	public void setBodyMassIndex(Integer bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public Character getCough() {
		return cough;
	}
	public void setCough(Character cough) {
		this.cough = cough;
	}
	public Character getCold() {
		return cold;
	}
	public void setCold(Character cold) {
		this.cold = cold;
	}
	public Character getThroatPain() {
		return throatPain;
	}
	public void setThroatPain(Character throatPain) {
		this.throatPain = throatPain;
	}
	public Character getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(Character bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public Character getDiabetes() {
		return diabetes;
	}
	public void setDiabetes(Character diabetes) {
		this.diabetes = diabetes;
	}
	public Character getLungDisease() {
		return lungDisease;
	}
	public void setLungDisease(Character lungDisease) {
		this.lungDisease = lungDisease;
	}
	public Character getHeartPatient() {
		return heartPatient;
	}
	public void setHeartPatient(Character heartPatient) {
		this.heartPatient = heartPatient;
	}
	public Character getBoneFracture() {
		return boneFracture;
	}
	public void setBoneFracture(Character boneFracture) {
		this.boneFracture = boneFracture;
	}
	public Character getAccidentalHistory() {
		return accidentalHistory;
	}
	public void setAccidentalHistory(Character accidentalHistory) {
		this.accidentalHistory = accidentalHistory;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public Integer getJunkFoodConsumptionFreq() {
		return junkFoodConsumptionFreq;
	}
	public void setJunkFoodConsumptionFreq(Integer junkFoodConsumptionFreq) {
		this.junkFoodConsumptionFreq = junkFoodConsumptionFreq;
	}
	public Integer getExerciseDurationPerDay() {
		return exerciseDurationPerDay;
	}
	public void setExerciseDurationPerDay(Integer exerciseDurationPerDay) {
		this.exerciseDurationPerDay = exerciseDurationPerDay;
	}
	public Boolean getMedicalInsurance() {
		return medicalInsurance;
	}
	public void setMedicalInsurance(Boolean medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	
}
