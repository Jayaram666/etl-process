package brimmatech.etl.process.domain;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.JsonNode;

import brimmatech.etl.process.config.EmployeeValidationJsonNodeConverter;


@Entity
@Table( name = "employee")
public class EmployeeEntity {

	@Id
	@GeneratedValue
	@Column( name = "employee_id")
	private Integer employeeId;

	@Column( name = "firstName")
	private String firstName;

	@Column( name = "last_name")
	private String lasstName;

	@Column( name = "email")
	private String email;

	@Column( name = "birth_date")
	private LocalDate birthDate;

	@Column( name = "country")
	private String country;

	@Column( name = "company")
	private String company;

	@Column( name = "is_processed")
	private Boolean isProcessed;

	@Column( name = "validations")
	@Convert( converter = EmployeeValidationJsonNodeConverter.class)
	private JsonNode employeeValidations;

	@Column( name = "record_status")
	private String recordStatus;


	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLasstName() {
		return lasstName;
	}

	public void setLasstName(String lasstName) {
		this.lasstName = lasstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}


	public JsonNode getEmployeeValidations() {
		return employeeValidations;
	}


	public void setEmployeeValidations(JsonNode employeeValidations) {
		this.employeeValidations = employeeValidations;
	}

	public String getRecordStatus() {
		return recordStatus;
	}


	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}


}
