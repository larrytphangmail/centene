package com.centene.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.centene.domain.Dependent;
import com.centene.domain.Enrollee;
import com.centene.domain.EnrolleeDO;
import com.centene.repos.DependentRepository;
import com.centene.repos.EnrolleeRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class EnrolleeService {

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	static final Logger log = LogManager.getLogger(EnrolleeService.class);
	
	
	@Autowired
	private EnrolleeRepository enrolleeRepo;
	
	@Autowired
	private DependentRepository dependentRepo;
	
	
	/**
	 * 
	 * @param status
	 * @return
	 */
	public List<Enrollee> getAllEnrolleesByActivationStatus(boolean status) {
		return enrolleeRepo.findByActivationStatus(status);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Enrollee> getAllEnrollees() {
		return enrolleeRepo.findAll();
	}
	
	/**
	 * 
	 * @param enrolleeId
	 * @return
	 */
	public Optional<Enrollee> findByEnrolleeId(long enrolleeId) {
		return enrolleeRepo.findById(enrolleeId);
	}
	
	
	/**
	 * 
	 * @param enrollee
	 * @return
	 */
	@Transactional
	public Enrollee addEnrollee(Enrollee enrollee) {
		return enrolleeRepo.saveAndFlush(enrollee);
	}
	
	/**
	 * 
	 * @param enrollees
	 * @return
	 */
	@Transactional
	public List<Enrollee> addEnrollees(List<Enrollee> enrollees) {
		return enrolleeRepo.saveAll(enrollees);
	}
	
	/**
	 * Update an Enrollee
	 * @param enrollee
	 * @return
	 */
	@Transactional
	public Enrollee updateEnrollee(EnrolleeDO enrolleeDO) {
		Optional<Enrollee> optEnrollee = enrolleeRepo.findById(enrolleeDO.getId());
		if(optEnrollee.isPresent()) {
			Enrollee enrollee = optEnrollee.get();
			enrollee.setActivationStatus(enrolleeDO.isActivationStatus());
			enrollee.setBirthDate(enrolleeDO.getBirthDate());
			enrollee.setName(enrolleeDO.getName());
			
			return enrolleeRepo.saveAndFlush(enrollee);
		}
		return new Enrollee(-1, "No Name With ID: "+enrolleeDO.getId());
	}
	
	/**
	 * 
	 * @param enrolleeDOList
	 * @return
	 */
	@Transactional
	public List<Enrollee> updateEnrollees(List<EnrolleeDO> enrolleeDOList) {
	
		List<Enrollee> enrolleeList = new ArrayList<>();
		enrolleeDOList.forEach(
				(e) -> {
					       enrolleeList.add(updateEnrollee(e));
				}
		);
		
		return enrolleeList;		
	}
	
	
	/**
	 * 
	 * @param enrollee
	 */
	@Transactional
	public void deleteEnrollee(EnrolleeDO enrolleeDO) {
		Optional<Enrollee> optEnrollee = enrolleeRepo.findById(enrolleeDO.getId());
		if(optEnrollee.isPresent()) {
			enrolleeRepo.delete(optEnrollee.get());
		}
		
	}
	
	/**
	 * 
	 * @param enrollees
	 */
	@Transactional
	public void deleteEnrollees(List<EnrolleeDO> enrollees) {
		List<Enrollee> enrolleeList = new ArrayList<>();
		enrollees.forEach( 
				(e) -> {
							Optional<Enrollee> optEnrollee = enrolleeRepo.findById(e.getId());
							if(optEnrollee.isPresent()) {
								enrolleeList.add(optEnrollee.get());
							}						  
				}
		);
		enrolleeRepo.deleteAll(enrolleeList);
	}
	
	/**
	 * 
	 * @param enrolleeId
	 */
	@Transactional
	public void deleteEnrolleeById(long enrolleeId) {
		enrolleeRepo.deleteById(enrolleeId);
	}
	
	/**
	 * 
	 * @param enrolleeId
	 * @return
	 */
	public Optional<Dependent> findDependentByEnrolleeId(Long enrolleeId) {
		return dependentRepo.findById(enrolleeId);
	}
	
	/**
	 * Add Dependent to Enrollee
	 * @param dependent
	 * @return
	 */
	@Transactional
	public Dependent addDependent(Dependent dependent) {
		return dependentRepo.saveAndFlush(dependent);
	}
	
	/**
	 * 
	 * @param dependents
	 * @return
	 */
	@Transactional
	public List<Dependent> addDependents(Set<Dependent> dependents) {
		return dependentRepo.saveAll(dependents);
	}
	
	/**
	 * 
	 * @param dependent
	 */
	@Transactional
	public void deleteDependent(Dependent dependent) {
		dependentRepo.delete(dependent);
	}
	
	
}
