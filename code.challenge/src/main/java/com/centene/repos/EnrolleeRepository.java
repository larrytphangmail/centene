package com.centene.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.centene.domain.Enrollee;

@EnableJpaRepositories
public interface EnrolleeRepository extends JpaRepository<Enrollee, Long>{

	@Query("SELECT e FROM Enrollee e WHERE e.activationStatus = ?1")
	public List<Enrollee> findByActivationStatus(boolean status);
}
