package com.centene.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.centene.domain.Dependent;
import com.centene.domain.DependentDO;
import com.centene.domain.Enrollee;
import com.centene.repos.DependentRepository;
import com.centene.repos.EnrolleeRepository;

@Service
public class DependentService {
	
	static final Logger log = LogManager.getLogger(DependentService.class);
		
	@Autowired
	private DependentRepository dependentRepo;
	
	@Autowired
	private EnrolleeRepository enrolleeRepo;
	
	
	/**
	 * Get All Dependents
	 * @return
	 */
	public List<Dependent> getAllDependents() {
		return dependentRepo.findAllDependents();
	}
	
	
	/**
	 * 
	 * @param enrolleeId
	 * @return
	 */
	public List<Dependent> getDependentByEnrolleeId(long enrolleeId) {
		return dependentRepo.findByEnrolleeId(enrolleeId);
	}
	
	/**
	 * 
	 * @param dependent
	 * @return
	 */
	@Transactional
	public Dependent addDependent(Dependent dependent) {
		Optional<Enrollee> optEnrollee = enrolleeRepo.findById(dependent.getEnrolee().getId());
		if(optEnrollee.isPresent()) {
			return dependentRepo.saveAndFlush(dependent);
		}
		return new Dependent(-1, "Cannot Add 'Dependent', No Enrollee With Id: "+dependent.getEnrolee().getId());		
	}
	
	
	/**
	 * 
	 * @param dependentDOs
	 * @return
	 */
	@Transactional
	public List<Dependent> addDependents(List<DependentDO> dependentDOs) {
		List<Dependent> dependents = new ArrayList<>();
		dependentDOs.forEach( 
				(d) -> {
							dependents.add(
					    		  new Dependent(d.getName(), d.getBirthDate(), new Enrollee(d.getEnrolleId()))
					    );
				}
		);
		dependentRepo.saveAll(dependents);
		
		return dependents;
	}
	
	
	/**
	 * 
	 * @param dependent
	 * @return
	 */
	@Transactional
	public List<Dependent> updateDependent(DependentDO dependent) {
		List<Dependent> dependents = dependentRepo.findByEnrolleeId(dependent.getEnrolleId());		
		dependents.forEach( 
				(d) -> {
						   d.setEnrolee(new Enrollee(dependent.getEnrolleId()));
					       d.setBirthDate(dependent.getBirthDate());
					       d.setName(dependent.getName());
				}
		);
		
		return dependentRepo.saveAll(dependents);
	}
	
	
	/**
	 * 
	 * @param dependentDO
	 * @return
	 */
	@Transactional
	public Dependent updateDependentById(DependentDO dependentDO) {
		Optional<Dependent> dependent = dependentRepo.findById(dependentDO.getId());
		if(dependent.isPresent()) {
			
			Optional<Enrollee> optEnrollee = enrolleeRepo.findById(dependentDO.getEnrolleId());
			if(optEnrollee.isPresent()) {
				Dependent dept = dependent.get();
				dept.setId(dependentDO.getId());
				dept.setBirthDate(dependentDO.getBirthDate());
				dept.setName(dependentDO.getName());
				dept.setEnrolee(optEnrollee.get());
				
				return dependentRepo.saveAndFlush(dept);
				
			}		
			
		}
		
		return new Dependent(-1,"Not Exist with ID: "+dependentDO.getId());
	}
	
	/**
	 * 
	 * @param enrollee
	 */
	@Transactional
	public void deleteDependent(Enrollee enrollee) {
		dependentRepo.deleteDependentByEnrolleeId(enrollee.getId());
	}
	
	/**
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteDependentByEnrolleeId(String id) {
		List<Dependent> dependents = dependentRepo.findByEnrolleeId(new Long(id));
		dependentRepo.deleteAll(dependents);		
	}
	
	/**
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteDependentById(String id) {
		Optional<Dependent> optDependent = dependentRepo.findById(new Long(id));
		if(optDependent.isPresent()) {
			dependentRepo.delete(optDependent.get());
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteDependentByIds(List<Long> ids) {
		List<Dependent> dependentList = dependentRepo.findAllById(ids);
	    dependentRepo.deleteAll(dependentList);
	}
	
	
	
}
