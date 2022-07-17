package com.employee.repository_Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee.entity.Country;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Integer>
{

//	@Modifying
//	@Query("update Country c set c.cname=:b where c.cid=:a")
//	public void updateCountry(@Param("a") int cid,@Param("b") String cname);
}
