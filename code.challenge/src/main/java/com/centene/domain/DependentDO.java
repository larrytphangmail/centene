package com.centene.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class DependentDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String birthDate;
	private long enrolleId;
	private EnrolleeDO enrolleeDO;
	
	public DependentDO() {
		super();
	}

	
	public DependentDO(long id, String name, String birthDate, EnrolleeDO enrolleeDO) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.enrolleeDO = enrolleeDO;
	}

	

	public DependentDO(long id, String name, String birthDate, long enrolleId, EnrolleeDO enrolleeDO) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.enrolleId = enrolleId;
		this.enrolleeDO = enrolleeDO;
	}


	public DependentDO(String name, String birthDate, long enrolleId) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.enrolleId = enrolleId;
	}

	public DependentDO(long id, String name, String birthDate, long enrolleId) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.enrolleId = enrolleId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public long getEnrolleId() {
		return enrolleId;
	}

	public void setEnrolleId(long enrolleId) {
		this.enrolleId = enrolleId;
	}

	public EnrolleeDO getEnrolleeDO() {
		return enrolleeDO;
	}

	public void setEnrolleeDO(EnrolleeDO enrolleeDO) {
		this.enrolleeDO = enrolleeDO;
	}

}
