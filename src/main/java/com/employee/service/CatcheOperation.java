package com.employee.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.employee.entity.Country;
import com.employee.entity.Employee;
import com.employee.entity.Registration;
import com.employee.repository_Dao.CountryRepository;
import com.employee.repository_Dao.EmployeeRepository;
import com.employee.repository_Dao.RegistrationRepository;

@Service
public class CatcheOperation {

	@Autowired
	EmployeeRepository repo;

	@Autowired
	CountryRepository countryRepo;

	@Autowired
	RegistrationRepository registartionRepo;
	
	
	//EMPLOYEE DATA TO HASH MAP
	public static HashMap<Integer, Employee> catche=new HashMap<>();
	public static List<Employee> employeList=new ArrayList<>();

	@Scheduled(cron = "0 */15 * ? * *")  // scheduled performance 
	@PostConstruct  // work as init method as load when application started as once 
	public void getEmployeListToCatche()
	{
		System.out.println("Employee--> Catche is loaded time "+new Date());
		employeList=repo.findAll();
		if(!employeList.isEmpty())
		{
			catche.clear();
			employeList.forEach(t -> catche.put(t.getEid(), t));
			catche.values().forEach(t -> System.err.println(t));
		}
	}


	
	//COUNTRY DATA TO HASH MAP
	public static HashMap<Integer, Country> catcheCountry=new HashMap<>();
	public static List<Country> countryList=new ArrayList<>();

	@Scheduled(cron = "0 */15 * ? * *")  // scheduled performance 
	@PostConstruct  // work as init method as load when application started as once 
	public void getCountryListToCatche()
	{
		System.out.println("Country---> Catche is loaded time "+new Date());
		countryList=countryRepo.findAll();
		if(!countryList.isEmpty())
		{
			catcheCountry.clear();
			countryList.forEach(t -> catcheCountry.put(t.getCid(), t));
			catcheCountry.values().forEach(t -> System.err.println(t));
		}
	}
	
	
	//REGISTRATION DATA TO HASH MAP
	public static HashMap<Integer, Registration> catcheRegistration=new HashMap<>();
	public static List<Registration> registrationList=new ArrayList<>();

	@Scheduled(cron = "0 */15 * ? * *")  // scheduled performance 
	@PostConstruct  // work as init method as load when application started as once 
	public void getRegistartionListToCatche()
	{
		System.out.println("Registration---> Catche is loaded time "+new Date());
		registrationList=registartionRepo.findAll();
		if(!registrationList.isEmpty())
		{
			catcheRegistration.clear();
			registrationList.forEach(t -> catcheRegistration.put(t.getRegistrationid(), t));
			catcheRegistration.values().forEach(t -> System.err.println(t));
		}
	}

}
