package com.paypal.bfs.test.employeeserv.businessImpl;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * a common utility class that is returned 
 * from operations which have either a success or failure
 * as return type only. 
 * 
 */
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationResult implements Serializable {

	private String explanation = "OK";
	private Boolean success = true;

	public ValidationResult(String explanation, Boolean success) {
		super();
		this.explanation = explanation;
		this.success = success;
	}

	public ValidationResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String toString() {
		return "Success=" + success + ", explanation=" + explanation;
	}

}
