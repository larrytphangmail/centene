package com.centene.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.centene.domain.Dependent;
import com.centene.domain.DependentDO;
import com.centene.domain.Enrollee;
import com.centene.domain.EnrolleeDO;
import com.centene.service.DependentService;
import com.centene.service.EnrolleeService;
import com.centene.util.CenteneUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author larrytphan
 *
 */
@RestController
public class CenteneController {

	static final Logger log = LogManager.getLogger(CenteneController.class);

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	private EnrolleeService enrolleeService;

	@Autowired
	private DependentService dependentService;

	public CenteneController() {

		log.info("Starting EnrolleeController - Available URL Paths: ");
		log.info("\nAvailable URL Paths:");
		log.info("/get-all-enrollees/activation-status/{status}");
		log.info("/get-all-enrollees");
		log.info("/get-all-enrollees/enrollee-id/{enrolleeId}");
		log.info("/add-enrollee");
		log.info("/delete-enrollee");
		log.info("/delete-enrollee/id/{id}");
		log.info("/update-enrollee");
		log.info("/add-dependent");
		log.info("/add-dependents");
		log.info("/get-all-dependents");
		log.info("/get-all-dependents/enrollee-id/{enrolleeId}");
		log.info("/update-dependent-by-enrolleeId");
		log.info("/update-dependent-by-id");
		log.info("/delete-dependent/enrollee-id/{id}");
		log.info("/delete-dependent/id/{id}");
		log.info("/delete-dependents");

	}

	/**
	 * ENROLLEE
	 */

	/**
	 * 
	 * @param status
	 * @return
	 */
	@GetMapping(path = "/get-all-enrollees/activation-status/{status}")
	public List<EnrolleeDO> getAllEnrolleeByActivationStatus(@PathVariable("status") boolean status) {
		List<Enrollee> enrolleeList = enrolleeService.getAllEnrolleesByActivationStatus(status);

		return CenteneUtil.convertToEnrolleeDO(enrolleeList);

	}

	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/get-all-enrollees", produces = "application/json")
	public List<EnrolleeDO> getAllEnrollees() {
		List<Enrollee> enrolleeList = enrolleeService.getAllEnrollees();
		return CenteneUtil.convertToEnrolleeDO(enrolleeList);
	}

	/**
	 * 
	 * @param enrolleeId
	 * @return
	 */
	@GetMapping(path = "/get-all-enrollees/enrollee-id/{enrolleeId}")
	public EnrolleeDO getAllEnrolleeById(@PathVariable("enrolleeId") String enrolleeId) {
		Optional<Enrollee> optEnrollee = enrolleeService.findByEnrolleeId(new Long(enrolleeId));
		if (optEnrollee.isPresent()) {
			return CenteneUtil.convertToEnrolleeDO(optEnrollee.get());
		}
		return new EnrolleeDO(-1, "Not Exist with ID: " + enrolleeId);
	}

	/**
	 * 
	 * @param enrollee
	 * @return
	 */
	@PostMapping(path = "/add-enrollee", consumes = "application/json")
	public EnrolleeDO addEnrollee(@RequestBody Enrollee enrollee) {
		log.info("Add Enrollee");
		log.info(gson.toJson(enrollee));
		return CenteneUtil.convertToEnrolleeDO(enrolleeService.addEnrollee(enrollee));
	}

	/**
	 * 
	 * @param enrollee
	 * @return
	 */
	@PostMapping(path = "/add-enrollees", consumes = "application/json")
	public List<EnrolleeDO> addEnrollees(@RequestBody List<Enrollee> enrollees) {
		log.info("Add Enrollees");
		log.info(gson.toJson(enrollees));
		return CenteneUtil.convertToEnrolleeDO(enrolleeService.addEnrollees(enrollees));
	}

	/**
	 * 
	 * @param enrollee
	 */
	@PostMapping(path = "/delete-enrollee", consumes = "application/json")
	public void deleteEnrollee(@RequestBody EnrolleeDO enrolleeDO) {
		log.info("Delete Enrollee: " + gson.toJson(enrolleeDO));

		enrolleeService.deleteEnrollee(enrolleeDO);
	}

	/**
	 * 
	 * @param enrollee
	 */
	@PostMapping(path = "/delete-enrollees", consumes = "application/json")
	public void deleteEnrollees(@RequestBody List<EnrolleeDO> enrolleeDOs) {
		log.info("Delete Enrollee: " + gson.toJson(enrolleeDOs));

		enrolleeService.deleteEnrollees(enrolleeDOs);
	}

	/**
	 * 
	 * @param enrollee
	 */
	@PostMapping(path = "/delete-enrollee/id/{id}")
	public void deleteEnrolleeById(@PathVariable("id") String id) {
		log.info("Delete Enrollee Id: " + id);
		enrolleeService.deleteEnrolleeById(new Long(id));
	}

	/**
	 * 
	 * @param enrollee
	 * @return
	 */
	@PostMapping(path = "/update-enrollee", consumes = "application/json")
	public EnrolleeDO updateEnrollee(@RequestBody EnrolleeDO enrolleeDO) {
		log.info("Update Enrollee");
		return CenteneUtil.convertToEnrolleeDO(enrolleeService.updateEnrollee(enrolleeDO));
	}

	/**
	 * 
	 * @param enrolleeDOList
	 * @return
	 */
	@PostMapping(path = "/update-enrollees", consumes = "application/json")
	public List<EnrolleeDO> updateEnrollees(@RequestBody List<EnrolleeDO> enrolleeDOList) {
		log.info("Update Collection of Enrollees");

		return CenteneUtil.convertToEnrolleeDO(enrolleeService.updateEnrollees(enrolleeDOList));
	}

	/**
	 * DEPENDENTS
	 */

	/**
	 * 
	 * @param dependentDO
	 * @return
	 */
	@PostMapping(path = "/add-dependent", consumes = "application/json")
	public DependentDO addDependent(@RequestBody DependentDO dependentDO) {
		log.info("Add Dependent");
		log.info(gson.toJson(dependentDO));
		Dependent dependent = dependentService.addDependent(new Dependent(dependentDO.getName(),
				dependentDO.getBirthDate(), new Enrollee(new Long(dependentDO.getEnrolleId()))));

		return CenteneUtil.convertToDependentDO(dependent);
	}

	/**
	 * 
	 * @param dependentDO
	 * @return
	 */
	@PostMapping(path = "/add-dependents", consumes = "application/json")
	public List<DependentDO> addDependent(@RequestBody List<DependentDO> dependentDOs) {
		log.info("Add Dependent");
		log.info(gson.toJson(dependentDOs));
		List<DependentDO> dependents = new ArrayList<>();
		dependentService.addDependents(dependentDOs).forEach((d) -> {
			dependents.add(new DependentDO(d.getId(), d.getName(), d.getBirthDate(), d.getEnrolee().getId()));
		});

		return dependents;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/get-all-dependents", produces = "application/json")
	public List<DependentDO> getAllDependent() {
		List<Dependent> dependents = dependentService.getAllDependents();
		return CenteneUtil.convertToDependentDO(dependents);
	}

	/**
	 * 
	 * @param enrolleeId
	 * @return
	 */
	@GetMapping(path = "/get-all-dependents/enrollee-id/{enrolleeId}", produces = "application/json")
	public List<DependentDO> getAllDependentByEnrolleeId(@PathVariable("enrolleeId") String enrolleeId) {
		List<Dependent> dependents = dependentService.getDependentByEnrolleeId(new Long(enrolleeId));
		return CenteneUtil.convertToDependentDO(dependents);
	}

	/**
	 * 
	 * @param dependent
	 * @return
	 */
	@PostMapping(path = "/update-dependent-by-enrolleeId", consumes = "application/json")
	public List<DependentDO> updateDependent(@RequestBody DependentDO dependent) {
		log.info(gson.toJson(dependent));
		List<Dependent> dependents = dependentService.updateDependent(dependent);
		return CenteneUtil.convertToDependentDO(dependents);
	}

	/**
	 * 
	 * @param dependent
	 * @return
	 */
	@PostMapping(path = "/update-dependent-by-id", consumes = "application/json")
	public DependentDO updateDependentById(@RequestBody DependentDO dependentDO) {
		log.info(gson.toJson(dependentDO));
		return CenteneUtil.convertToDependentDO(dependentService.updateDependentById(dependentDO));
	}

	/**
	 * 
	 * @param id
	 */
	@PostMapping(path = "/delete-dependent/enrollee-id/{id}")
	public void deleteDependentByEnrolleeId(@PathVariable("id") String id) {
		log.info(gson.toJson(id));
		dependentService.deleteDependentByEnrolleeId(id);
	}

	/**
	 * 
	 * @param id
	 */
	@PostMapping(path = "/delete-dependent/id/{id}")
	public void deleteDependentById(@PathVariable("id") String id) {
		log.info(gson.toJson(id));
		dependentService.deleteDependentById(id);
	}

	/**
	 * 
	 * @param dependentIds
	 */
	@PostMapping(path = "/delete-dependents")
	public void deleteDependentByIds(@RequestBody List<DependentDO> dependentIds) {
		log.info(gson.toJson(dependentIds));
		List<Long> ids = new ArrayList<>();
		dependentIds.forEach((d) -> {
			ids.add(d.getId());
		});
		dependentService.deleteDependentByIds(ids);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
