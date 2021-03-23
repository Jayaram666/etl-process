package brimmatech.etl.process.domain;


public class EmployeeValidationDTO {

	private Integer validationId;
	private String validation;
	private String validationMessage;


	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public Integer getValidationId() {
		return validationId;
	}

	public void setValidationId(Integer validationId) {
		this.validationId = validationId;
	}

}
