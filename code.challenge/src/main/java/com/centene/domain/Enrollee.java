package com.centene.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "enrollee")
public class Enrollee implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private boolean activationStatus;
	private String birthDate;
	private String phoneNumber;
	
	@OneToMany(mappedBy = "enrolee", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Dependent> dependents;

	public Enrollee() {
		super();
	}

	public Enrollee(long id) {
		super();
		this.id = id;
	}
	
	public Enrollee(String name) {
		super();
		this.name = name;
	}
	
	public Enrollee(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Enrollee(String name, boolean activationStatus, String birthDate, String phoneNumber) {
		super();
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	public Enrollee(long id, String name, boolean activationStatus, String birthDate, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	
	public Enrollee(String name, boolean activationStatus, String birthDate, String phoneNumber, List<Dependent> dependents) {
		super();
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.dependents = dependents;
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

	public List<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}

	@Override
	public String toString() {
		return "Enrollee [id=" + id + ", name=" + name + ", activationStatus=" + activationStatus + ", birthDate="
				+ birthDate + ", phoneNumber=" + phoneNumber +  "]";
	}

}
