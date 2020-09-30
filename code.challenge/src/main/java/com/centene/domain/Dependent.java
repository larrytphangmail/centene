package com.centene.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dependent")
public class Dependent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String birthDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "enrollee_id", nullable = false)
	@JsonIgnore
	private Enrollee enrolee;

	public Dependent() {
		super();
	}

	public Dependent(long id) {
		super();
		this.id = id;
	}

	public Dependent(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Dependent(long id, String name, String birthDate, Enrollee enrolee) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.enrolee = enrolee;
	}

	public Dependent(String name, String birthDate, Enrollee enrolee) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.enrolee = enrolee;
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

	public Enrollee getEnrolee() {
		return enrolee;
	}

	public void setEnrolee(Enrollee enrolee) {
		this.enrolee = enrolee;
	}

	@Override
	public String toString() {
		return "Dependent [id=" + id + ", name=" + name + ", birthDate=" + birthDate+ "]";
	}

}
