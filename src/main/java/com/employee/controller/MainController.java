package com.employee.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.employee.entity.Country;
import com.employee.entity.Employee;
import com.employee.repository_Dao.CountryRepository;
import com.employee.repository_Dao.EmployeeRepository;
import com.employee.service.AutoGenaratedIdClass;
import com.employee.service.CatcheOperation;

@RestController
@CrossOrigin
public class MainController {

	@Autowired
	EmployeeRepository repo;

	@Autowired
	CountryRepository repoCountry;

	@PostMapping("/saveemployee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee)
	{
		System.err.println("Create Employee Query Fiared"+employee);
		employee.setEid(AutoGenaratedIdClass.getAutoIdForEmployeeId());//set employee id and created date
		employee.setCreateddate(new Date());//set created date for employee
		CatcheOperation.catche.put(employee.getEid(), employee);	//update hash map
		
		if(CatcheOperation.catche.get(employee.getEid())!=null)
		{
			return new ResponseEntity<Employee>(repo.save(employee),HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<String>("Data Not Save!!",HttpStatus.BAD_GATEWAY);
		}
		
		
	}

	@GetMapping("/getemployeebyid/{employeeid}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int employeeid)
	{
		System.err.println("Get Employee by id Query Fiared and ID is -"+employeeid);
		return new ResponseEntity<Employee>(CatcheOperation.catche.get(employeeid),HttpStatus.ACCEPTED);
	}

	@GetMapping("/getallemployee")
	public ResponseEntity<List<Employee>> getAllEmployee()
	{
		System.err.println("Get All Employee Query Fiared");
		return new ResponseEntity<List<Employee>>(CatcheOperation.catche.values().stream().toList(),HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateemployee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee)
	{
		System.err.println("Update Employee Query Fiared");
		Employee employee2 = CatcheOperation.catche.get(employee.getEid());//cretedDate getting and setting
		employee.setCreateddate(employee2.getCreateddate());
		
		employee.setUpdateddate(new Date());//updateDate seted

		//hashmap update
		CatcheOperation.catche.remove(employee.getEid());
		CatcheOperation.catche.put(employee.getEid(), employee);


		return new ResponseEntity<Employee>( repo.save(employee),HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteemployee/{employeeid}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int employeeid)
	{
		System.err.println("Delete Employee Query Fiared");
		//data remove from data base by custom query in repository
		repo.deleteEmployeeById(employeeid);

		//data remove from hash map 
		CatcheOperation.catcheCountry.remove(employeeid);

		return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
	}


	//COUNTRY
	@PostMapping("/savecountry")
	public ResponseEntity<?> saveCountry(@RequestBody Country country)
	{
		System.err.println("Create Country Query Fiared");
		return new ResponseEntity<Country>( repoCountry.save(country),HttpStatus.ACCEPTED);
	}

	@GetMapping("/getcountrybyemployeid/{countryid}")
	public ResponseEntity<?> getCountryByCountryId(@PathVariable int countryid) 
	{
		System.err.println("Get Country By id Query Fiared");
		return new ResponseEntity<Country>(CatcheOperation.catcheCountry.get(countryid),HttpStatus.ACCEPTED);
	}

	@GetMapping("/getallcountry")
	public ResponseEntity<List<Country>>  getAllCountry()
	{
		System.err.println("Get All Country Query Fiared");
		return new ResponseEntity<List<Country>>( CatcheOperation.catcheCountry.values().stream().toList(),HttpStatus.ACCEPTED);
	}

	@PutMapping("/updatecountry")
	public ResponseEntity<?> updateCountryBycountryId(@RequestBody Country country)
	{
		System.err.println("Update Country Query Fiared");
		//hashmap update
		CatcheOperation.catcheCountry.remove(country.getCid());
		CatcheOperation.catcheCountry.put(country.getCid(), country);

		return new ResponseEntity<Country>( repoCountry.save(country),HttpStatus.ACCEPTED);
	}

	@DeleteMapping("deletecountry/{countryid}")
	public ResponseEntity<?> deleteCountry(@PathVariable int countryid)
	{
		System.err.println("Delete Country Query Fiared");
		//data remove from data base
		repoCountry.deleteById(countryid);

		//data remove from hash map 
		CatcheOperation.catcheCountry.remove(countryid);

		return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
	}


	//Microservices-->One to one mapping
	@GetMapping("/getcountrybyemployeeid/{employeeid}")
	public ResponseEntity<?> getCountryByEmployeeId(@PathVariable int employeeid)
	{
		System.err.println("Get Country by Employee ID Query Fiared");
		return new ResponseEntity<Country>(CatcheOperation.catche.get(employeeid).getCountry(),HttpStatus.ACCEPTED);
	}

	@PutMapping("/updatecountrybyemployeeid/{employeeid}")
	public ResponseEntity<?> updateCountryByEmployeeId(@RequestBody Country country,@PathVariable int employeeid)
	{
		System.err.println("Update Country by Employee ID Query Fiared");
		//get country from employee id
		Country countryCopy = CatcheOperation.catche.get(employeeid).getCountry();	
		countryCopy.setCid(country.getCid());
		countryCopy.setCname(country.getCname());

		//get data from hashmap
		Employee employee2 = CatcheOperation.catche.get(employeeid);

		//set country data to employee
		employee2.setCountry(countryCopy);

		//set update date 
		employee2.setUpdateddate(new Date());

		repo.save(employee2);

		return new ResponseEntity<String>("Updated Sucessfully",HttpStatus.ACCEPTED);
	}
}
