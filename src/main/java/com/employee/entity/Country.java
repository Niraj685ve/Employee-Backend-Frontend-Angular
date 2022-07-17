package com.employee.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Country {

	@Id
	int cid;
	String cname;
}
