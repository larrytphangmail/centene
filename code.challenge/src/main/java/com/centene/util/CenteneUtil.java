package com.centene.util;

import java.util.ArrayList;
import java.util.List;

import com.centene.domain.Dependent;
import com.centene.domain.DependentDO;
import com.centene.domain.Enrollee;
import com.centene.domain.EnrolleeDO;

public final class CenteneUtil {

	private CenteneUtil() {
		
	}
	
	/**
	 * 
	 * @param enrollees
	 * @return
	 */
	public static List<EnrolleeDO> convertToEnrolleeDO(List<Enrollee> enrollees) {
		List<EnrolleeDO> enrolleeDOList = new ArrayList<>();
		
		enrollees.forEach( 
				(e) -> {
					        List<DependentDO> dependentDOList = new ArrayList<>();
					        
					        List<Dependent> dependentList = e.getDependents();
					        if(dependentList != null) {
					        	dependentList.forEach( 
						        		(d) -> {
						        			dependentDOList.add( 
							        				new DependentDO(
							        									d.getId(), 
							        									d.getName(), 
							        									d.getBirthDate(), 
							        									convertToDenpendentEnrolleeDO(
							        											d.getEnrolee()
							        									)
							        				)
							        		);						        		
						        		}					        		
						        );
					        }
					        
					        
					        enrolleeDOList.add(
					        		new EnrolleeDO( 
							        				e.getId(),
							        				e.getName(),
							        				e.isActivationStatus(),
							        				e.getBirthDate(),
							        				e.getPhoneNumber(),
							        			    dependentDOList
					        		)
					        );
				}
		);
		
		return enrolleeDOList;
	}
	
	/**
	 * 
	 * @param enrollee
	 * @return
	 */
	public static EnrolleeDO convertToEnrolleeDO(Enrollee enrollee) {
		 List<DependentDO> dependentDOList = new ArrayList<>();
	       
		 if(enrollee != null) {
			 List<Dependent> dependentsList = enrollee.getDependents();
			 if(dependentsList != null) {
			 
				 dependentsList.forEach( 
		        		(d) -> {
		        			dependentDOList.add( 
			        				new DependentDO(
			        									d.getId(), d.getName(), d.getBirthDate(), 
			        									convertToDenpendentEnrolleeDO(d.getEnrolee())
			        				)
			        		);
		        		}
		        		
				 );
			 }
			EnrolleeDO enrolleeDO = new EnrolleeDO( 
													enrollee.getId(),
													enrollee.getName(),
													enrollee.isActivationStatus(),
													enrollee.getBirthDate(),
													enrollee.getPhoneNumber(),
												    dependentDOList
												);
			
			return enrolleeDO;
		 }
		 
		 return new EnrolleeDO();
		
	}
	
	
	
	/**
	 * 
	 * @param dependent
	 * @return
	 */
	public static List<DependentDO> convertToDependentDO(List<Dependent> dependents) {
		
		List<DependentDO> dependentDOList = new ArrayList<>();
		
		dependents.forEach( 
				(d) -> {
						dependentDOList.add( 
								convertToDependentDO(d) 
					    );
				}
		);
		
		return dependentDOList;
		
	}
	
	/**
	 * 
	 * @param dependent
	 * @return
	 */
	public static DependentDO convertToDependentDO(Dependent dependent) {
		
		return new DependentDO(
								dependent.getId(), 
								dependent.getName(), 
								dependent.getBirthDate(), 
								(
										dependent.getEnrolee() == null ? 0 :  dependent.getEnrolee().getId()
								),
								convertToEnrolleeDO(
										dependent.getEnrolee()
							    )
		);				
	
	}
	
	
	/**
	 * 
	 * @param enrollee
	 * @return
	 */
	public static EnrolleeDO convertToDenpendentEnrolleeDO(Enrollee enrollee) {
		return new EnrolleeDO(
				enrollee.getId(),
				enrollee.getName(),
				enrollee.isActivationStatus(),
				enrollee.getBirthDate(),
				enrollee.getPhoneNumber()
				);
	}
	
}
