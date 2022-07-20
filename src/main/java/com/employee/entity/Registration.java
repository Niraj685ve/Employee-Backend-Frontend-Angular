package com.employee.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = ("email")))
@Data
public class Registration {

	@Id
	int registrationid;
	String firstname;
	String lastname;
	String username;
	String mobilenumber;
	String email;
	String password;
	String gender;
	Date createddate;
}
