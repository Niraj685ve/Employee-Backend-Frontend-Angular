package com.employee.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service	
public class AutoGenaratedIdClass {

	//Auto genarated for registartion id
	public static int getAutoIDForRegistrationAdminORUser(String mobilenumber)
	{
		int eidautogenarated=Integer.parseInt(new SimpleDateFormat("mmsss").format(new Date()).concat(mobilenumber.substring(3, 6)));
		return eidautogenarated;
	}
	
	
	//Auto generated For employee id
	public static int getAutoIdForEmployeeId()
	{
		String autogenarateID=new SimpleDateFormat("MMddHHmmss").format(new Date());
		int eidautogenarated=Integer.parseInt(autogenarateID);
		return eidautogenarated;
	}
}
