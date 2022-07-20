package com.employee.repository_Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer>{

}
