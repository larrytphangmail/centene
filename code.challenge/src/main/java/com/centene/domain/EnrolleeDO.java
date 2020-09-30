package com.centene.domain;

import java.io.Serializable;
import java.util.List;

public class EnrolleeDO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private boolean activationStatus;
	private String birthDate;
	private String phoneNumber;
	
	private List<DependentDO> dependentDOList;

	
	public EnrolleeDO() {
		super();
	}
	
	public EnrolleeDO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public EnrolleeDO(long id, String name, boolean activationStatus, String birthDate, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	public EnrolleeDO(String name, boolean activationStatus, String birthDate, String phoneNumber, List<DependentDO> dependentDOList) {
		super();
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.dependentDOList = dependentDOList;
	}

	public EnrolleeDO(long id, String name, boolean activationStatus, String birthDate, String phoneNumber,	List<DependentDO> dependentDOList) {
		super();
		this.id = id;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.dependentDOList = dependentDOList;
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

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<DependentDO> getDependentDOList() {
		return dependentDOList;
	}

	public void setDependentDOList(List<DependentDO> dependentDOList) {
		this.dependentDOList = dependentDOList;
	}
	
	


}
