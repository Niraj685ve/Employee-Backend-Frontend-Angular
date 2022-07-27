package com.employee.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



import lombok.Data;

@Entity
@Data
public class Employee 
{
	@Id
	private int eid;
	private String ename;
	private String contactnumber;
	private String emailid;
	private String gender;
	private String department;
	private String status = "inactive";
	private Date createddate;	
	private String createdby;
	private String updatedby;
	private Date updateddate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cid_ref")
	private Country country;
}
