package com.employee.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.employee.entity.Registration;
import com.employee.repository_Dao.RegistrationRepository;
import com.employee.service.AutoGenaratedIdClass;
import com.employee.service.CatcheOperation;
import com.employee.service.EmailSending;

@RestController
@CrossOrigin
public class RegistrationLoginController {

	@Autowired
	RegistrationRepository repo;


	@PostMapping("/registration")
	public ResponseEntity<?> getRegistration(@RequestBody Registration registration) throws Exception
	{
		//set registration id for register admin/user
		registration.setRegistrationid(AutoGenaratedIdClass.getAutoIDForRegistrationAdminORUser(registration.getMobilenumber()));

		//set createdDate
		registration.setCreateddate(new Date());

		//sending mail 
		String sex;
		if(registration.getGender().equals("male")) sex="sir";
		else sex="madam";
			
		String massage="Hellow "+registration.getFirstname()+" "+sex+
				",\nYour registration ID is "+registration.getRegistrationid()+
				" Username "+registration.getUsername()+
				" and Password is "+registration.getPassword()+
				"\nThank you for choosing\nHave a nice Day\n"+new Date(); String
				Subject="Registration Sucessfully!"; String to=registration.getEmail();
				EmailSending.sendEmail(massage,Subject,to);



				return new ResponseEntity<Registration>(repo.save(registration),HttpStatus.ACCEPTED);
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Registration registration)
	{
		HashMap<Object, Object> hashMap=new HashMap<>();
		List<Integer> key=new ArrayList<>();
		key.clear();
		//if Registration id is provided
		if(registration.getRegistrationid()!=0)
		{
			key.add(registration.getRegistrationid());
		}

		//if registration id is provided
		if(registration.getRegistrationid()==0 && registration.getEmail()!=null)
		{
			//by stream API get registration id through email and send to List array
			CatcheOperation.catcheRegistration.entrySet().
			stream().
			filter(t -> t.getValue().getEmail().equals(registration.getEmail())).
			forEach(t -> key.add(t.getKey()));
		}


		//by for Each 
		//			CatcheOperation.catcheRegistration.forEach((t, u) -> {
		//			System.out.println("for each enter :: "+t+" "+u );
		//			if(u.getEmail().equals(registration.getEmail()))
		//			{
		//				System.out.println(t);
		//			}
		//		});


		//1)BELOW IF bracket check--->id is available or not && username and password is available or not && [username check from hashMap || email check from hash map] && password check from data base
		if(CatcheOperation.catcheRegistration.get(key.get(0))!=null && registration!=null && 
				(CatcheOperation.catcheRegistration.get(key.get(0)).getRegistrationid()==registration.getRegistrationid() || 
				CatcheOperation.catcheRegistration.get(key.get(0)).getEmail().equals(registration.getEmail())) &&
				CatcheOperation.catcheRegistration.get(key.get(0)).getPassword().equals(registration.getPassword()))
		{	

			hashMap.put("msgSucess", "Valid User");
			hashMap.put("data",CatcheOperation.catcheRegistration.get(key.get(0)));
			return new ResponseEntity<>(hashMap,HttpStatus.ACCEPTED);
		}
		else
		{
			hashMap.put("msgFail", "Not Valid User");
			return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
		}
	}

}
