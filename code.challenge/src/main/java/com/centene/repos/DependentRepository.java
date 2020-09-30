package com.centene.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.centene.domain.Dependent;

@EnableJpaRepositories
public interface DependentRepository extends JpaRepository<Dependent, Long>{

	@Query(value = "Delete From Dependent d WHERE d.enrolee_id = ?1", nativeQuery = true)
	public void deleteDependentByEnrolleeId(long enrolleeId);
	

	@Query(value = "select * from dependent d where d.enrollee_id = ?1", nativeQuery = true)
	public List<Dependent> findByEnrolleeId(long enrolleeId);
	
	@Query(value = "select * from dependent d", nativeQuery = true)
	public List<Dependent> findAllDependents();
	
	
	
}
