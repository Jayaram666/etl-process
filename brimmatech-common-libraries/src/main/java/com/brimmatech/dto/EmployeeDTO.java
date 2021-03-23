package com.brimmatech.dto;


import java.time.LocalDate;


public class EmployeeDTO {

	private Integer employeeId;

	private String firstName;

	private String lasstName;

	private String email;

	private LocalDate birthDate;

	private String country;

	private String company;


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

	public EmployeeDTO (Integer employeeId , String firstName , String lasstName , String email , LocalDate birthDate ,
			String country , String company) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lasstName = lasstName;
		this.email = email;
		this.birthDate = birthDate;
		this.country = country;
		this.company = company;
	}

	public EmployeeDTO () {
	}

}
