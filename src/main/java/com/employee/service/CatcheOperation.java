package com.employee.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.employee.repository_Dao.CountryRepository;
import com.employee.repository_Dao.EmployeeRepository;

@Service
public class CatcheOperation {

	@Autowired
	EmployeeRepository repo;

	@Autowired
	CountryRepository countryRepo;

	public static HashMap<Integer, Employee> catche=new HashMap<>();
	public static List<Employee> employeList=new ArrayList<>();

	@Scheduled(cron = "0 */5 * ? * *")  // scheduled performance 
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


	public static HashMap<Integer, Country> catcheCountry=new HashMap<>();
	public static List<Country> countryList=new ArrayList<>();

	@Scheduled(cron = "0 */5 * ? * *")  // scheduled performance 
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




	//Auto generated eid
	public static int getAutoIdForEmployeeId()
	{
		String autogenarateID=new SimpleDateFormat("MMddHHmmss").format(new Date());
		int eidautogenarated=Integer.parseInt(autogenarateID);
		return eidautogenarated;
	}

	
	//createdDate
	public static Date getCreatedDateForEmployee()
	{
		String createdDate =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		Date date=null;
		try {
			date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(createdDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	//updatedDate
		public static Date getupdateDateForEmployee()
		{
			String createdDate =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			Date date=null;
			try {
				date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(createdDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}

}
