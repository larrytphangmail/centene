package com.centene;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.centene.domain.Enrollee;
import com.centene.service.EnrolleeService;

@SpringBootTest
class CenteneApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeAll
	static void initAll() {
		
	}

	@BeforeEach
	void init() {
		
	}
	
	
	@Autowired
	private EnrolleeService enrolleeService;
	
	
	@Test
	void testGetAllEnrolleesByActivationStatus() {
		List<Enrollee> list = enrolleeService.getAllEnrolleesByActivationStatus(true);
		assertNotNull(list);
	}
	
	
	

	@AfterEach
	void tearDown() {
		
	}

	@AfterAll
	static void tearDownAll() {
		
	}
}
